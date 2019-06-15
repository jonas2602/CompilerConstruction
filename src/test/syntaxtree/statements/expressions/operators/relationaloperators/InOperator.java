package test.syntaxtree.statements.expressions.operators.relationaloperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class InOperator extends Operator {

    public InOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " in " + right + " ) ";
    }
}
