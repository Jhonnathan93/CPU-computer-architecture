/**
 * The ROM (Read-Only Memory) class simulates a read-only memory in a digital circuit.
 * It allows for reading instructions at specified memory positions.
 */
public class ROM {

    // Program counter
    short pc;
    // Array representing the memory, with a size of 32K words
    short[] memory = new short[32 * 1024]; 

    /**
     * Returns the instruction stored at the specified memory position.
     * 
     * @param PC the memory position to read from
     * @return the instruction stored at the specified memory position
     * @throws IllegalArgumentException if the PC is out of bounds
     */
    public short getInstruction(short PC) {
        if (PC < 0 || PC >= memory.length) {
            throw new IllegalArgumentException("PC out of bounds");
        }
        return memory[PC];
    }

    /**
     * Sets the instruction at the specified memory position.
     * 
     * @param position the memory position to write to
     * @param instruction the instruction to store at the specified position
     * @throws IllegalArgumentException if the position is out of bounds
     */
    public void setInstruction(short position, short instruction) {
        if (position < 0 || position >= memory.length) {
            throw new IllegalArgumentException("PC out of bounds");
        }
        memory[position] = instruction;
    }
}
