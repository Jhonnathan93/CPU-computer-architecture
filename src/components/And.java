/**
 * The And class simulates an AND gate in a digital circuit.
 * It performs a bitwise AND operation on two input values.
 */
public class And {
    // Input variables
    private short a;
    private short b;

    // Output variable
    private short out;

    /**
     * Sets the first input value for the AND gate.
     * 
     * @param a the first input value
     */
    public void setA(short a) { this.a = a; }

    /**
     * Sets the second input value for the AND gate.
     * 
     * @param b the second input value
     */
    public void setB(short b) { this.b = b; }

    /**
     * Returns the output value of the AND gate.
     * 
     * @return the output value
     */
    public short getOut() { return out; }

    /**
     * Performs the bitwise AND operation on the input values
     * and stores the result in the output variable.
     */
    public void compute() {
        out = (short) (a & b);
    }
}
