package test.syntaxtree.statements.expressions.operators.multiplicativeoperators;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.BoolConstant;
import test.syntaxtree.statements.expressions.operators.Operator;

public class AndOperator extends Operator {

    public AndOperator(ScopeNode parent) {
        super(parent);
    }

    public Node check() {
        if(left instanceof BoolConstant && right instanceof BoolConstant) {
            BoolConstant b = new BoolConstant(parentBlock);
            b.setValue(((BoolConstant)left).getValue() && ((BoolConstant)right).getValue());
            return b;
        }
        return this;
    }

    @Override
    public String toString() {
        return " ( " + left + " and " + right + " ) ";
    }
}
