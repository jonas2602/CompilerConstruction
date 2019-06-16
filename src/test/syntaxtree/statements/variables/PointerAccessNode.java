package test.syntaxtree.statements.variables;

import test.syntaxtree.scopes.ScopeNode;

public class PointerAccessNode extends VariableAccessNode {

    public PointerAccessNode(ScopeNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "^";
    }
}
