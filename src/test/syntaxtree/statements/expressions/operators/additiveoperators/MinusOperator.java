package test.syntaxtree.statements.expressions.operators.additiveoperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class MinusOperator extends Operator {

    public MinusOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " - " + right + " ) ";
    }
}
