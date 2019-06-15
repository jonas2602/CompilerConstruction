package test.syntaxtree.statements.expressions.operators.relationaloperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class GreaterThanOperator extends Operator {

    public GreaterThanOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " > " + right + " ) ";
    }
}
