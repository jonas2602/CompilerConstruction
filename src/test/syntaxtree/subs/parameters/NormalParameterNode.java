package test.syntaxtree.subs.parameters;

import test.syntaxtree.BlockNode;

public class NormalParameterNode extends ParameterNode {

    public NormalParameterNode(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "NOR "+identifier+" "+type;
    }

    @Override
    public void print(int level) {
        super.print(this);
    }
}
