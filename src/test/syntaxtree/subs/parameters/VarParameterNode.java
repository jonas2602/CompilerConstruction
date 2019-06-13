package test.syntaxtree.subs.parameters;

import test.syntaxtree.BlockNode;

public class VarParameterNode extends ParameterNode {

    public VarParameterNode(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "VAR "+identifier+" "+type;
    }

    @Override
    public void print(int level) {
        super.print(this);
    }
}
