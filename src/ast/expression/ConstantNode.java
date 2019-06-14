package ast.expression;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class ConstantNode extends AbstractSyntaxTree {
    String m_Data;
    TypeNode m_Type;

    public ConstantNode(String InData, TypeNode InType) {
        this.m_Data = InData;
        this.m_Type = InType;
    }

    @Override
    public TypeNode CheckType() {
        return GetType();
    }

    @Override
    public TypeNode GetType() {
        return m_Type;
    }
}
