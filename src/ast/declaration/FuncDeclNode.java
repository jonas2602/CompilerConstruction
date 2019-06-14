package ast.declaration;

import ast.AbstractSyntaxTree;

import java.util.List;

public class FuncDeclNode extends AbstractSyntaxTree {
    private String m_Name;
    private List<AbstractSyntaxTree> m_Params;
    private AbstractSyntaxTree m_ReturnType;
    private AbstractSyntaxTree m_Block;

    public FuncDeclNode(String InName, List<AbstractSyntaxTree> InParams, AbstractSyntaxTree InReturnType, AbstractSyntaxTree InBlock){
        m_Name = InName;
        m_Params = InParams;
        m_ReturnType = InReturnType;
        m_Block = InBlock;
    }

    public String GetName(){
        return m_Name;
    }
}
