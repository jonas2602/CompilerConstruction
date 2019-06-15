package test.syntaxtree.statements.expressions.operators.relationaloperators;

import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.BoolConstant;
import test.syntaxtree.constants.NumberConstant;
import test.syntaxtree.constants.RealConstant;
import test.syntaxtree.statements.expressions.operators.Operator;

public class EqualOperator extends Operator {

    public EqualOperator(BlockNode parent) {
        super(parent);
    }

    public Node check() {
        if(left instanceof NumberConstant && right instanceof NumberConstant) {
            BoolConstant b = new BoolConstant(parentBlock);
            b.setValue(NumberConstant.getDobuleValue((NumberConstant)left) == NumberConstant.getDobuleValue((NumberConstant)right));
            return b;
        }
        return this;
    }

    @Override
    public String toString() {
        return " ( " + left + " = " + right + " ) ";
    }
}
