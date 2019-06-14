package ast.declaration;

import ast.AbstractSyntaxTree;

public class ProcDeclNode extends AbstractSyntaxTree {
    private String m_Name;
    private AbstractSyntaxTree[] m_Params;
    private AbstractSyntaxTree m_Block;

    public ProcDeclNode(String InName, AbstractSyntaxTree[] InParams, AbstractSyntaxTree InBlock){
        m_Name = InName;
        m_Params = InParams;
        m_Block = InBlock;
    }

    public String GetName(){
        return m_Name;
    }
}