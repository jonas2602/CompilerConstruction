package test.syntaxtree.statements.expressions.operators.multiplicativeoperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class AndOperator extends Operator {

    public AndOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " and " + right + " ) ";
    }
}
