package test.syntaxtree.statements.expressions.operators.additiveoperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.BoolConstant;
import test.syntaxtree.constants.IntConstant;
import test.syntaxtree.constants.NumberConstant;
import test.syntaxtree.constants.RealConstant;
import test.syntaxtree.statements.expressions.operators.Operator;

public class OrOperator extends Operator {

    public OrOperator(BlockNode parent) {
        super(parent);
    }

    public Node check() {
        if(left instanceof BoolConstant && right instanceof BoolConstant) {
            BoolConstant b = new BoolConstant(parentBlock);
            b.setValue(((BoolConstant)left).getValue() || ((BoolConstant)right).getValue());
            return b;
        }
        return this;
    }

    @Override
    public String toString() {
        return " ( " + left + " or " + right + " ) ";
    }
}
