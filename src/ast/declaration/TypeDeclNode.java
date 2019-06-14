package ast.declaration;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class TypeDeclNode extends AbstractSyntaxTree {
    String m_Name;
    TypeNode m_Type;

    public TypeDeclNode(String InName, TypeNode InType) {
        this.m_Name = InName;
        this.m_Type = InType;
    }

    public String GetName(){
        return m_Name;
    }
}
