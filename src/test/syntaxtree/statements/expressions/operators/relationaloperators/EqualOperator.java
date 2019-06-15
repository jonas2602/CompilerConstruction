package test.syntaxtree.statements.expressions.operators.relationaloperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class EqualOperator extends Operator {

    public EqualOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " = " + right + " ) ";
    }
}
