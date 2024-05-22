/**
 * The RAM class simulates a Random Access Memory (RAM) in a digital circuit.
 * It allows for reading and writing data at specified memory positions.
 */
public class RAM {
    
    // Array representing the memory, with a size of 16K words
    short[] memory = new short[16 * 1024]; 

    /**
     * Returns the value stored at the specified memory position.
     * 
     * @param position the memory position to read from
     * @return the value stored at the specified memory position
     * @throws IllegalArgumentException if the position is out of bounds
     */
    public short getValue(short position) {
        if (position < 0 || position >= memory.length) {
            throw new IllegalArgumentException("PC out of bounds");
        }
        return memory[position];
    }

    
    /**
     * Sets the value at the specified memory position.
     * 
     * @param position the memory position to write to
     * @param instruction the value to store at the specified position
     * @throws IllegalArgumentException if the position is out of bounds
     */
    public void setValue(short position, short instruction) {
        if (position < 0 || position >= memory.length) {
            throw new IllegalArgumentException("PC out of bounds");
        }
        memory[position] = instruction;
    }
}
