package ast.declaration;

import ast.AbstractSyntaxTree;

public class TypeDeclNode extends AbstractSyntaxTree {
    String m_Name;
    AbstractSyntaxTree m_Type;

    public TypeDeclNode(String InName, AbstractSyntaxTree InType) {
        this.m_Name = InName;
        this.m_Type = InType;
    }
}
