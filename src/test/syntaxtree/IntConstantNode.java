package test.syntaxtree;

public class IntConstantNode extends Node {
    private int value;

    public IntConstantNode() {
        super();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
       return this.value;
    }
}
