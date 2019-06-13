package ast.declaration;

import ast.AbstractSyntaxTree;

public class VarDeclNode extends AbstractSyntaxTree {
    String m_Name;
    AbstractSyntaxTree m_Type;

    public VarDeclNode(String InName, AbstractSyntaxTree InType) {
        this.m_Name = InName;
        this.m_Type = InType;
    }
}
