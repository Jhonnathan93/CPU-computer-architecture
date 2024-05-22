/**
 * The Register class simulates a register in a digital circuit.
 * It stores a value that can be loaded or retrieved.
 */
public class Register {
    // The value stored in the register
    private short value;

    /**
     * Sets the value of the register.
     * 
     * @param value the new value for the register
     */
    public void setValue(short value) { 
        this.value = value; 
    }

    /**
     * Returns the current value of the register.
     * 
     * @return the current value
     */
    public short getValue() { 
        return value; 
    }


    /**
     * Loads a new value into the register if the load signal is true.
     * 
     * @param in the new value to load
     * @param load the load signal
     */
    public void load(short in, boolean load) {
        if (load) {
            value = in;
        }
    }    
}
