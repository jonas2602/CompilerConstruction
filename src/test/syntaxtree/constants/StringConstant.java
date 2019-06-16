package test.syntaxtree.constants;

import test.syntaxtree.scopes.ScopeNode;

public class StringConstant extends Constant{

    private String value;

    public StringConstant(ScopeNode parent) {
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
