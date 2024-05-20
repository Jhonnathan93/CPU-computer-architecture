package src.components;

public class Multiplexor {
    private short a;
    private short b;
    private short sel;
    private short result;

    public void setInputs(short a, short b, short sel) {
        this.a = a;
        this.b = b;
        this.sel = sel;
    }

    public short getResult() {
        return result;
    }

    public void compute() {
        result = 0;

        for (int i = 0; i < 16; i++) {
            short bitA = Word.getBit(a, i);
            short bitB = Word.getBit(b, i);

            short selectedBit = (sel == 0) ? bitA : bitB;

            result = (short) (result | (selectedBit << i));
        }
    }
}