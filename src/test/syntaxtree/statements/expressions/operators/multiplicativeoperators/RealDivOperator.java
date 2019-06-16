package test.syntaxtree.statements.expressions.operators.multiplicativeoperators;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.NumberConstant;
import test.syntaxtree.constants.RealConstant;
import test.syntaxtree.statements.expressions.operators.Operator;

public class RealDivOperator extends Operator {

    public RealDivOperator(ScopeNode parent) {
        super(parent);
    }

    public Node check() {
        if(left instanceof NumberConstant && right instanceof NumberConstant) {
            RealConstant r = new RealConstant(parentBlock);
            r.setValue(NumberConstant.getDobuleValue((NumberConstant)left) + NumberConstant.getDobuleValue((NumberConstant)right));
            return r;
        }
        return this;
    }

    @Override
    public String toString() {
        return " ( " + left + " / " + right + " ) ";
    }
}
