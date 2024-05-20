package src.components;

public class Not {
    private short in;
    private short out;

    // Setter
    public void setIn(short in) { this.in = in; }

    // Getter
    public short getOut() { return out; }

    // Compute method
    public void compute() {
        out = (short) ~in;
    }
}

