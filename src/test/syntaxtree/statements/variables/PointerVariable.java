package test.syntaxtree.statements.variables;

import test.syntaxtree.BlockNode;

public class PointerVariable extends VariableNode {

    public PointerVariable(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "@"+super.toString();
    }
}
