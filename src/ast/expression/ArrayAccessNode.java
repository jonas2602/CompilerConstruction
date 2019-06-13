package ast.expression;

import ast.AbstractSyntaxTree;

public class ArrayAccessNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Child;
    private AbstractSyntaxTree[] m_IndexExpressions;

    public ArrayAccessNode(AbstractSyntaxTree InChild, AbstractSyntaxTree[] InIndexExpressions) {
        this.m_Child = InChild;
        this.m_IndexExpressions = InIndexExpressions;
    }
}
