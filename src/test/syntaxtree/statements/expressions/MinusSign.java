package test.syntaxtree.statements.expressions;

import test.syntaxtree.BlockNode;
import test.syntaxtree.Node;

public class MinusSign extends Node {

    private Node term;

    public MinusSign(BlockNode parent) {
        super(parent);
    }

    public Node getTerm() {
        return term;
    }

    public void setTerm(Node term) {
        this.term = term;
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
