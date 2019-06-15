package ast.expressions;

import ast.AbstractSyntaxTree;

public class PointerAccessNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Child;

    public PointerAccessNode(AbstractSyntaxTree InChild) {
        this.m_Child = InChild;
        m_Child.SetParent(this);
    }

    // TODO: Type Checking
}
