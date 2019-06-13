package test.syntaxtree.subs.parameters;

import test.syntaxtree.BlockNode;

public class FunctionParameterNode extends ParameterNode {

    public FunctionParameterNode(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "FUC "+identifier+" "+type;
    }

    @Override
    public void print(int level) {
        super.print(this);
    }
}
