package test.syntaxtree.statements.expressions.operators.multiplicativeoperators;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.IntConstant;
import test.syntaxtree.statements.expressions.operators.Operator;

public class IntDivOperator extends Operator {

    public IntDivOperator(ScopeNode parent) {
        super(parent);
    }

    public Node check() {
        if(left instanceof IntConstant && right instanceof IntConstant) {
            IntConstant i = new IntConstant(parentBlock);
            i.setValue(((IntConstant)left).getValue() / ((IntConstant)right).getValue());
            return i;
        }

        return this;
    }

    @Override
    public String toString() {
        return " ( " + left + " div " + right + " ) ";
    }
}
