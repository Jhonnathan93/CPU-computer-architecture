public class ControlUnit {
    private Or or1;
    private Or or2;
    private Or or3;
    private Not not1;
    private Not not2;
    private Not not3;
    private And and1;
    private And and2;
    private And and3;
    private And and4;
    private And and5;
    private And and6;
    private And and7;
    private And and8;
    private Multiplexor mux1;
    private Multiplexor mux2;
    private Register aRegister;
    private Register dRegister;
    private ALU alu;
    private PC pc;

    private short inM;
    private short instruction;
    private boolean reset;

    private short outM;
    private boolean writeM;
    private short addressM;
    private short pcOut;

    private ROM rom;
    private RAM ram;

    public ControlUnit() {
        or1 = new Or();
        or2 = new Or();
        or3 = new Or();
        not1 = new Not();
        not2 = new Not();
        not3 = new Not();
        and1 = new And();
        and2 = new And();
        and3 = new And();
        and4 = new And();
        and5 = new And();
        and6 = new And();
        and7 = new And();
        and8 = new And();
        mux1 = new Multiplexor();
        mux2 = new Multiplexor();
        aRegister = new Register();
        dRegister = new Register();
        alu = new ALU();
        pc = new PC();
        ram = new RAM();
        rom = new ROM();

        // Initialize inputs
        inM = 0;
        instruction = rom.getInstruction((short) 0);

        // Initialize outputs
        outM = 0;
        addressM = 0;
        pcOut = 0;
    }

    public void setInM(short inM) {
        this.inM = inM;
    }

    public void setInstruction(short instruction) {
        this.instruction = instruction;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public short getOutM() {
        return outM;
    }

    public boolean isWriteM() {
        return writeM;
    }

    public short getAddressM() {
        return addressM;
    }

    public short getPcOut() {
        return pcOut;
    }

    public void compute() {
        // Decode which instruction to execute
        or1.setA(Word.getBit(instruction, 15));
        or1.setB((short) 0); // b=false
        or1.compute();
        short isCInstruction = or1.getOut();

        not1.setIn(Word.getBit(instruction, 15));
        not1.compute();
        short isAInstruction = not1.getOut();

        and1.setA(isCInstruction);
        and1.setB(Word.getBit(instruction, 5));
        and1.compute();
        short isCWriteA = and1.getOut();

        or2.setA(isAInstruction);
        or2.setB(isCWriteA);
        or2.compute();
        short loadA = or2.getOut();

        mux1.setInputs(instruction, alu.out, isCWriteA);
        mux1.compute();
        short inAReg = mux1.getResult();

        aRegister.load(inAReg, loadA == 1);
        short outAReg = aRegister.getValue();

        addressM = Word.extractBits(outAReg, 0, 14);
        
        and2.setA(isCInstruction);
        and2.setB(Word.getBit(instruction, 4));
        and2.compute();
        short loadD = and2.getOut();

        dRegister.load(alu.out, loadD == 1);
        short outDReg = dRegister.getValue();

        mux2.setInputs(outAReg, inM, Word.getBit(instruction, 12));
        mux2.compute();
        short outAorM = mux2.getResult();

        alu.x = outDReg;
        alu.y = outAorM;
        alu.zx = Word.getBit(instruction, 11);
        alu.nx = Word.getBit(instruction, 10);
        alu.zy = Word.getBit(instruction, 9);
        alu.ny = Word.getBit(instruction, 8);
        alu.f = Word.getBit(instruction, 7);
        alu.no = Word.getBit(instruction, 6);
        alu.compute();
        short outALU = alu.out;

        outM = outALU;

        not2.setIn(alu.ng);
        not2.compute();
        short isNonNeg = not2.getOut();

        not3.setIn(alu.zr);
        not3.compute();
        short isNonZero = not3.getOut();

        and3.setA(isNonNeg);
        and3.setB(isNonZero);
        and3.compute();
        short isPositive = and3.getOut();

        and4.setA(isCInstruction);
        and4.setB(Word.getBit(instruction, 3));
        and4.compute();
        writeM = and4.getOut() == 1;

        and5.setA(isPositive);
        and5.setB(Word.getBit(instruction, 0));
        and5.compute();
        short JGT = and5.getOut();

        and6.setA(alu.zr);
        and6.setB(Word.getBit(instruction, 1));
        and6.compute();
        short JEQ = and6.getOut();

        and7.setA(alu.ng);
        and7.setB(Word.getBit(instruction, 2));
        and7.compute();
        short JLT = and7.getOut();

        or3.setA(JEQ);
        or3.setB(JLT);
        or3.compute();
        short JLE = or3.getOut();

        or2.setA(JLE);
        or2.setB(JGT);
        or2.compute();
        short jumpToA = or2.getOut();

        and8.setA(isCInstruction);
        and8.setB(jumpToA);
        and8.compute();
        short loadPC = and8.getOut();

        not1.setIn(loadPC);
        not1.compute();
        // Quizas aqui haya error
        short PCinc = not1.getOut();

        pc.load(outAReg, loadPC == 1);
        pc.inc();
        pc.reset(reset);
    }

    public static void main(String[] args) {
        ControlUnit controlUnit = new ControlUnit();
        controlUnit.rom.setInstruction((short) 0, (short) 0b0000000000000000);
        controlUnit.rom.setInstruction((short) 1, (short) 0b1111110000010000);
        controlUnit.rom.setInstruction((short) 2, (short) 0b0000000000000001);
        controlUnit.rom.setInstruction((short) 3, (short) 0b1111000010010000);
        controlUnit.rom.setInstruction((short) 4, (short) 0b0000000000000010);
        controlUnit.rom.setInstruction((short) 5, (short) 0b1110001100001000);

        controlUnit.ram.setValue((short) 0, (short) 0b0000000000000101);
        controlUnit.ram.setValue((short) 1, (short) 0b0000000000000011);

        for (int i = 0; i < 6; i++) {
            controlUnit.compute();
            short outM = controlUnit.getOutM();
            boolean writeM = controlUnit.isWriteM();
            short addressM = controlUnit.getAddressM();
            short pcOut = controlUnit.getPcOut();

            System.out.println("OutM: " + outM);
            System.out.println("WriteM: " + writeM);
            System.out.println("AddressM: " + addressM);
            System.out.println("PC: " + pcOut);
        }
    }
}
