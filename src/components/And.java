package src.components;

public class And {
    private short a;
    private short b;
    private short out;

    // Setters
    public void setA(short a) { this.a = a; }
    public void setB(short b) { this.b = b; }

    // Getter
    public short getOut() { return out; }

    // Compute method
    public void compute() {
        out = (short) (a & b);
    }
}
