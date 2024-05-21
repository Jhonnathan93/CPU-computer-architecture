public class RAM {
    short[] memory = new short[16 * 1024]; 

    public short getValue(short position) {
        if (position < 0 || position >= memory.length) {
            throw new IllegalArgumentException("PC out of bounds");
        }
        return memory[position];
    }

    public void setValue(short position, short instruction) {
        if (position < 0 || position >= memory.length) {
            throw new IllegalArgumentException("PC out of bounds");
        }
        memory[position] = instruction;
    }
}
