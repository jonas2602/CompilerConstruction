package test.syntaxtree.statements.expressions.operators.multiplicativeoperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.statements.expressions.operators.Operator;

public class ModOperator extends Operator {

    public ModOperator(BlockNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return " ( " + left + " % " + right + " ) ";
    }
}
