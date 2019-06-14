package ast.declaration;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class VarDeclNode extends AbstractSyntaxTree {
    String m_Name;
    TypeNode m_Type;

    public VarDeclNode(String InName, TypeNode InType) {
        this.m_Name = InName;
        this.m_Type = InType;
        m_Type.SetParent(this);
    }

    public String GetName() {
        return m_Name;
    }

    @Override
    public TypeNode CheckType() {
        m_Type.CheckType();
        return null;
    }
}
