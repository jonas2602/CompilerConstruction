package test.syntaxtree.constants;

import test.syntaxtree.scopes.ScopeNode;

public class BoolConstant extends Constant {
    private boolean value;

    public BoolConstant(ScopeNode parent) {
        super(parent);
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return " (bool) "+value;
    }
}
