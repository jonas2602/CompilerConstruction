package test.syntaxtree.statements.expressions.operators.multiplicativeoperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class IntDivOperator extends Operator {

    public IntDivOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " div " + right + " ) ";
    }
}
