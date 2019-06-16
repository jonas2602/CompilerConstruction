package test.syntaxtree.statements.expressions.operators;

import test.syntaxtree.scopes.ScopeNode;
import test.syntaxtree.Node;

public abstract class Operator extends Node {
    protected Node left;
    protected Node right;

    public Operator(ScopeNode parent) {
        super(parent);
    }

    public Operator(ScopeNode parent, Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public void print(int level) {
        super.print(level, this);
    }

    public abstract Node check();
}
