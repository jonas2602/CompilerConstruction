package test.syntaxtree.constants;

import test.syntaxtree.scopes.ScopeNode;

public class IntConstant extends NumberConstant {

    private int value;

    public IntConstant(ScopeNode parent) {
        super(parent);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "(int) " + value;
    }
}
