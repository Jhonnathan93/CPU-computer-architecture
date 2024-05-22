/**
 * The PC (Program Counter) class simulates a program counter in a digital circuit.
 * It keeps track of the address of the next instruction to be executed.
 */
public class PC {
    
    // The current value of the program counter
    private short value;

     /**
     * Sets the value of the program counter.
     * 
     * @param value the new value for the program counter
     */
    public void setValue(short value) {
        this.value = value; 
    }

     /**
     * Returns the current value of the program counter.
     * 
     * @return the current value
     */
    public short getValue() { 
        return value; 
    }

    /**
     * Increments the value of the program counter by one.
     */
    public void inc() {
        value++; 
    }

     /**
     * Loads a new value into the program counter if the load signal is true.
     * 
     * @param in the new value to load
     * @param load the load signal
     */
    public void load(short in, boolean load) {
        if (load) {
            value = in;
        }
    }

    
    /**
     * Resets the value of the program counter to zero if the reset signal is true.
     * 
     * @param reset the reset signal
     */
    public void reset(boolean reset) {
        if (reset) {
            value = 0;
        }
    }

}
