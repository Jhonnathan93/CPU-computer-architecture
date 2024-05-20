package src.components;

public class Or {
    private short a;
    private short b;
    private short out;

    public void setA(short a) { 
        this.a = a; 
    }

    public void setB(short b) { 
        this.b = b; 
    }

    public short getOut() {
        return out;
    }

    public void compute() {
        out = (short) (a | b);
    }
}
