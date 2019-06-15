package test.syntaxtree.statements.expressions.operators.relationaloperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class NotEqualOperator extends Operator {

    public NotEqualOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " != " + right + " ) ";
    }
}
