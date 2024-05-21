public class Not {
    private short in;
    private short out;

    public void setIn(short in) { this.in = in; }

    public short getOut() { return out; }

    public void compute() {
        out = (short) ~in;
    }
}

