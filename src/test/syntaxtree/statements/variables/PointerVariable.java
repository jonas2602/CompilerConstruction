package test.syntaxtree.statements.variables;

import test.syntaxtree.scopes.ScopeNode;

public class PointerVariable extends VariableNode {

    public PointerVariable(ScopeNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "@"+super.toString();
    }
}
