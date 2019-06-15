package test.syntaxtree.constants;

import test.syntaxtree.BlockNode;

public class CharConstant extends Constant{

    private char value;

    public CharConstant(BlockNode parent) {
        super(parent);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(char) " + value;
    }
}
