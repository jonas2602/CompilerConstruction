package test.syntaxtree.statements.variables;

import test.syntaxtree.BlockNode;

public class PointerAccessNode extends VariableAccessNode {

    public PointerAccessNode(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "^";
    }
}
