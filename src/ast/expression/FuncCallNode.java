package ast.expression;

import ast.AbstractSyntaxTree;

import java.util.List;

public class FuncCallNode extends AbstractSyntaxTree {
    private String m_ProcName;
    private List<AbstractSyntaxTree> m_Params;

    public FuncCallNode(String InProcName, List<AbstractSyntaxTree> InParams){
        this.m_ProcName = InProcName;
        this.m_Params = InParams;
    }
}
