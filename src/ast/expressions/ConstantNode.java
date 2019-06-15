package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class ConstantNode extends AbstractSyntaxTree {
    private String m_Data;
    private TypeNode m_Type;

    public ConstantNode(String InData, TypeNode InType) {
        this.m_Data = InData;
        this.m_Type = InType;

        m_Type.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        return m_Type.CheckType();
    }

    @Override
    public TypeNode GetType() {
        return m_Type.GetType();
    }
}
