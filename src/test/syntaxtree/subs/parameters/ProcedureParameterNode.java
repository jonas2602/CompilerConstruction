package test.syntaxtree.subs.parameters;

import test.syntaxtree.BlockNode;

public class ProcedureParameterNode extends ParameterNode {

    public ProcedureParameterNode(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "PRO "+identifier+" "+type;
    }

    @Override
    public void print(int level) {
        super.print(this);
    }
}
