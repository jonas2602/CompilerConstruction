package test.syntaxtree.statements.expressions;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.IntConstant;
import test.syntaxtree.constants.RealConstant;

public class MinusSign extends Node {

    private Node term;

    public MinusSign(ScopeNode parent) {
        super(parent);
    }

    public Node getTerm() {
        return term;
    }

    public void setTerm(Node term) {
        this.term = term;
    }

    public Node check() {
        if(term instanceof IntConstant) {
            IntConstant i = (IntConstant) term;
            i.setValue(- i.getValue());
            return i;
        }
        else if(term instanceof RealConstant) {
            RealConstant r = (RealConstant) term;
            r.setValue(- r.getValue());
            return r;
        }

        return this;
    }

    @Override
    public String toString() {
        return " -( "+term+" ) ";
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
