package test.syntaxtree.statements.expressions.operators.additiveoperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class OrOperator extends Operator {

    public OrOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " or " + right + " ) ";
    }
}
