/**
 * The Not class simulates a NOT gate in a digital circuit.
 * It performs a bitwise NOT operation on the input value.
 */
public class Not {

    //Input variable
    private short in;

    //Output variable
    private short out;

    /**
     * Sets the input value for the NOT gate.
     * 
     * @param in the input value
     */
    public void setIn(short in) { this.in = in; }

    /**
     * Returns the output value of the NOT gate.
     * 
     * @return the output value
     */
    public short getOut() { return out; }

     /**
     * Performs the bitwise NOT operation on the input value
     * and stores the result in the output variable.
     */
    public void compute() {
        out = (short) ~in;
    }
}

