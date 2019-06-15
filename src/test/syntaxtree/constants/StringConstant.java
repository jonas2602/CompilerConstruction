package test.syntaxtree.constants;

import test.syntaxtree.BlockNode;

public class StringConstant extends Constant{

    private String value;

    public StringConstant(BlockNode parent) {
        super(parent);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(str) " + value;
    }
}
