public class PC {
    private short value;

    public void setValue(short value) {
        this.value = value; 
    }

    public short getValue() { 
        return value; 
    }

    public void inc() {
        value++; 
    }

    public void load(short in, boolean load) {
        if (load) {
            value = in;
        }
    }

    public void reset(boolean reset) {
        if (reset) {
            value = 0;
        }
    }

}
