package test.syntaxtree.statements.expressions.operators.relationaloperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.BoolConstant;
import test.syntaxtree.constants.NumberConstant;
import test.syntaxtree.statements.expressions.operators.Operator;

public class InOperator extends Operator {

    public InOperator(BlockNode parent) {
        super(parent);
    }

    public Node check() {
        //TODO
        return this;
    }

    @Override
    public String toString() {
        return " ( " + left + " in " + right + " ) ";
    }
}
