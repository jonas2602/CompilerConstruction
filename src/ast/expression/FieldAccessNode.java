package ast.expression;

import ast.AbstractSyntaxTree;

public class FieldAccessNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Child;
    private String m_FieldName;

    public FieldAccessNode(AbstractSyntaxTree InChild, String InFieldName) {
        this.m_Child = InChild;
        this.m_FieldName = InFieldName;
    }
}
