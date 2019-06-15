package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class FieldAccessNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Child;
    private String m_FieldName;

    public FieldAccessNode(AbstractSyntaxTree InChild, String InFieldName) {
        this.m_Child = InChild;
        this.m_FieldName = InFieldName;

        m_Child.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode childType = m_Child.CheckType();

        // TODO:
        // child is Record?
        // has property with fieldname?
        // return property type
        return null;
    }
}
