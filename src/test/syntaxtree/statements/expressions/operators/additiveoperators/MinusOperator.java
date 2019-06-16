package test.syntaxtree.statements.expressions.operators.additiveoperators;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.IntConstant;
import test.syntaxtree.constants.NumberConstant;
import test.syntaxtree.constants.RealConstant;
import test.syntaxtree.statements.expressions.operators.Operator;

public class MinusOperator extends Operator {

    public MinusOperator(ScopeNode parent) {
        super(parent);
    }

    public Node check() {
        if(left instanceof NumberConstant && right instanceof NumberConstant) {
            if(left instanceof IntConstant && right instanceof IntConstant) {
                IntConstant i = new IntConstant(parentBlock);
                i.setValue(((IntConstant)left).getValue() - ((IntConstant)right).getValue());
                return i;
            }
            else {
                RealConstant r = new RealConstant(parentBlock);
                r.setValue(NumberConstant.getDobuleValue((NumberConstant)left) - NumberConstant.getDobuleValue((NumberConstant)right));
                return r;
            }
        }
        return this;
    }

    @Override
    public String toString() {
        return " ( " + left + " - " + right + " ) ";
    }
}
