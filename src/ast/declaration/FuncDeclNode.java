package ast.declaration;

import ast.AbstractSyntaxTree;

public class FuncDeclNode extends AbstractSyntaxTree {
    private String m_Name;
    private AbstractSyntaxTree[] m_Params;
    private AbstractSyntaxTree m_ReturnType;
    private AbstractSyntaxTree m_Block;

    public FuncDeclNode(String InName, AbstractSyntaxTree[] InParams, AbstractSyntaxTree InReturnType, AbstractSyntaxTree InBlock){
        m_Name = InName;
        m_Params = InParams;
        m_ReturnType = InReturnType;
        m_Block = InBlock;
    }
}
