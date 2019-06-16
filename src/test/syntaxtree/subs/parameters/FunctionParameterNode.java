package test.syntaxtree.subs.parameters;

import test.syntaxtree.scopes.ScopeNode;

public class FunctionParameterNode extends ParameterNode {

    public FunctionParameterNode(ScopeNode parent) {
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
