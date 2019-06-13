package test.syntaxtree;

public class LabelDeclarationNode extends Node {

    private int value;

    public LabelDeclarationNode() {
        super();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "LABELDEC "+value;
    }

    @Override
    public void print(int level) {
        println(level, this);
    }
}
