/**
 * The Or class simulates an OR gate in a digital circuit.
 * It performs a bitwise OR operation on two input values.
 */
public class Or {
    //Input variables
    private short a;
    private short b;

    //Output variable
    private short out;

    /**
     * Sets the first input value for the OR gate.
     * 
     * @param a the first input value
     */
    public void setA(short a) { 
        this.a = a; 
    }

    /**
     * Sets the second input value for the OR gate.
     * 
     * @param b the second input value
     */
    public void setB(short b) { 
        this.b = b; 
    }

    /**
     * Returns the output value of the OR gate.
     * 
     * @return the output value
     */
    public short getOut() {
        return out;
    }

    /**
     * Performs the bitwise OR operation on the input values
     * and stores the result in the output variable.
     */
    public void compute() {
        out = (short) (a | b);
    }
}
