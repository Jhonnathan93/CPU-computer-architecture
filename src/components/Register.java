package src.components;
public class Register {
    private short value;

    public void setValue(short value) { 
        this.value = value; 
    }

    public short getValue() { 
        return value; 
    }

    public void load(short in, boolean load) {
        if (load) {
            value = in;
        }
    }    
}
