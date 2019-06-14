package ast.declaration;

import ast.AbstractSyntaxTree;

public class ConstDeclNode extends AbstractSyntaxTree {
    private String m_Name;
    private AbstractSyntaxTree m_Constant;

    public ConstDeclNode(String InName, AbstractSyntaxTree InConstant){
        this.m_Name = InName;
        this.m_Constant = InConstant;
    }

    public String GetName(){
        return m_Name;
    }
}
