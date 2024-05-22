/**
 * The Multiplexor class simulates a digital multiplexer.
 * It selects one of the input values (a or b) based on the select signal (sel) and produces the result.
 */
public class Multiplexor {
    // Input variables
    private short a;
    private short b;
    private short sel;

    // Output variable
    private short result;

    /**
     * Sets the input values and the select signal for the multiplexer.
     * 
     * @param a the first input value
     * @param b the second input value
     * @param sel the select signal (0 or 1)
     */
    public void setInputs(short a, short b, short sel) {
        this.a = a;
        this.b = b;
        this.sel = sel;
    }

    /**
     * Returns the output value of the multiplexer.
     * 
     * @return the output value
     */
    public short getResult() {
        return result;
    }

    /**
     * Performs the selection operation based on the select signal (sel).
     * If sel is 0, the result is set to the value of a; otherwise, it is set to the value of b.
     */
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