package test.syntaxtree.statements.expressions;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;
import test.syntaxtree.constants.BoolConstant;

public class NotSign extends Node {

    private Node term;

    public NotSign(ScopeNode parent) {
        super(parent);
    }

    public Node getTerm() {
        return term;
    }

    public void setTerm(Node term) {
        this.term = term;
    }

    public Node check() {
        if(term instanceof BoolConstant) {
            BoolConstant b = (BoolConstant) term;
            b.setValue(! b.getValue());
            return b;
        }

        return this;
    }

    @Override
    public String toString() {
        return " !( "+term+" ) ";
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }
}
