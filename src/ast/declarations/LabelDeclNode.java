package ast.declarations;

import ast.AbstractSyntaxTree;

public class LabelDeclNode extends AbstractSyntaxTree {
    private String m_Name;

    public LabelDeclNode(String InName) {
        this.m_Name = InName;
    }

    public String GetName(){
        return m_Name;
    }
}
