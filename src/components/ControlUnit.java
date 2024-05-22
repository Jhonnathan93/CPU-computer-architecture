/**
 * The ControlUnit class simulates the functionality of a computer,
 * executing a set of instructions stored in ROM and manipulating data in RAM.
 */
public class ControlUnit {
    // Declaration of logic gates, ALU, registers, and PC
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

    // Input variables

    private short inM;
    private short instruction;
    private short instructionPosition;
    private boolean reset;

    // Output variables

    private short outDReg;
    private short outM;
    private boolean writeM;
    private short addressM;
    private short pcOut;

    // Memory

    private ROM rom;
    private RAM ram;

    /**
     * Constructs a ControlUnit and initializes its components.
     */

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
        instructionPosition = 0;
        instruction = rom.getInstruction(instructionPosition);

        // Initialize outputs
        outM = 0;
        outDReg = 0;
        addressM = 0;
        pcOut = 0;
    }

    /**
     * Sets the input value from memory.
     * 
     * @param inM the input value from memory
     */

    public void setInM(short inM) {
        this.inM = inM;
    }


     /**
     * Sets the current instruction.
     * 
     * @param instruction the current instruction
     */
    public void setInstruction(short instruction) {
        this.instruction = instruction;
    }

    /**
     * Sets the reset flag.
     * 
     * @param reset the reset flag
     */
    public void setReset(boolean reset) {
        this.reset = reset;
    }

     /**
     * Returns the output value to be written to memory.
     * 
     * @return the output value
     */

    public short getOutM() {
        return outM;
    }

    /**
     * Returns whether the memory should be written.
     * 
     * @return true if memory should be written, false otherwise
     */
    public boolean isWriteM() {
        return writeM;
    }

    /**
     * Returns the memory address to be accessed.
     * 
     * @return the memory address
     */
    public short getAddressM() {
        return addressM;
    }

    /**
     * Returns the current value of the program counter.
     * 
     * @return the program counter value
     */
    public short getPcOut() {
        return pcOut;
    }

     /**
     * Executes the computation process of the control unit.
     */
    public void compute() {
        // Decode which instruction to execute
        or1.setA(Word.getBit(instruction, 15));
        or1.setB((short) 0); // b=false
        or1.compute();
        short isCInstruction = or1.getOut();
    
        not1.setIn(Word.getBit(instruction, 15));
        not1.compute();
        short isAInstruction = not1.getOut();
        isAInstruction = (short) (isAInstruction & 1);
    
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
    
        addressM = outAReg;
    
        and2.setA(isCInstruction);
        and2.setB(Word.getBit(instruction, 4));
        and2.compute();
        short loadD = and2.getOut();
    
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

        // Loads ALU output to D Register
        dRegister.load(alu.out, loadD == 1);
        outDReg = dRegister.getValue();
    
        outM = outALU;
    
        not2.setIn(alu.ng);
        not2.compute();
        short isNonNeg = not2.getOut();
        isNonNeg = (short) (isNonNeg & 1);
    
        not3.setIn(alu.zr);
        not3.compute();
        short isNonZero = not3.getOut();
        isNonZero = (short) (isNonZero & 1);
    
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
        short PCinc = not1.getOut();
        PCinc = (short) (PCinc & 1);
    
        pc.load(outAReg, loadPC == 1);
        pc.inc();
        pcOut = pc.getValue();
        instructionPosition = pcOut;
        instruction = rom.getInstruction(instructionPosition);
        pc.reset(reset);
    }
    

    /**
     * Main method to run the simulation of the ControlUnit.
     * 
     * @param args command line arguments
     */
   
    public static void main(String[] args) {
        ControlUnit controlUnit = new ControlUnit();

        // Program to add two numbers from the RAM and store result in address 2
        controlUnit.rom.setInstruction((short) 0, (short) 0b0000000000000000); // @0
        controlUnit.rom.setInstruction((short) 1, (short) 0b1111110000010000); // D=M
        controlUnit.rom.setInstruction((short) 2, (short) 0b0000000000000001); // @1
        controlUnit.rom.setInstruction((short) 3, (short) 0b1111000010010000); // D=D+M
        controlUnit.rom.setInstruction((short) 4, (short) 0b0000000000000010); // @2
        controlUnit.rom.setInstruction((short) 5, (short) 0b1110001100001000); // M=D
    
        // Example values to test accuracy of program
        controlUnit.ram.setValue((short) 0, (short) 0b0000000000000101); // 5
        controlUnit.ram.setValue((short) 1, (short) 0b0000000000000011); // 3
    
        for (int i = 0; i < 6; i++) {
            controlUnit.inM = controlUnit.ram.getValue((short) controlUnit.addressM);
            controlUnit.compute();
            short outM = controlUnit.getOutM();
            boolean writeM = controlUnit.isWriteM();
            short addressM = controlUnit.getAddressM();
            short pcOut = controlUnit.getPcOut();
            short aluout = controlUnit.alu.out;

            if (writeM){
                controlUnit.ram.setValue(addressM, aluout);
            }
    
            System.out.println("PcOut" + pcOut);
            System.out.println("OutM: " + outM);
            System.out.println("WriteM: " + writeM);
            System.out.println("AddressM: " + addressM);
            System.out.println("PC: " + controlUnit.pc.getValue());
            System.out.println("ALU: " + controlUnit.alu.out);
            System.out.print("\n");
        }

        // Print statement to test result
        System.out.println("RAM[2]: " + controlUnit.ram.getValue((short) 2)); // should print 8
    }
    
}
