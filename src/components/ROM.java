public class ROM {
    short pc;
    short[] memory = new short[32 * 1024]; 

    public short getInstruction(short PC) {
        if (PC < 0 || PC >= memory.length) {
            throw new IllegalArgumentException("PC out of bounds");
        }
        return memory[PC];
    }

    public void setInstruction(short position, short instruction) {
        if (position < 0 || position >= memory.length) {
            throw new IllegalArgumentException("PC out of bounds");
        }
        memory[position] = instruction;
    }
}
