package test.syntaxtree.statements.expressions.operators.relationaloperators;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.statements.expressions.operators.Operator;

public class InOperator extends Operator {

    public InOperator(ScopeNode parent) {
        super(parent);
    }

    public Node check() {
        //TODO
        return this;
    }

    @Override
    public String toString() {
        return " ( " + left + " in " + right + " ) ";
    }
}
