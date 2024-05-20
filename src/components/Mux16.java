package src.components;
public class Mux16 {
    private short[] a = new short[16];
    private short[] b = new short[16];
    private boolean sel;

    private short[] out = new short[16];

    public void setA(short[] a) { 
        this.a = a; 
    }
    public void setB(short[] b) {
        this.b = b; 
    }
    public void setSel(boolean sel) {
        this.sel = sel; 
    }

    public short[] getOut() {
        return out;
    }

    public void compute() {
        for (int i = 0; i < 16; i++) {
            out[i] = sel ? b[i] : a[i];
        }
    }
}