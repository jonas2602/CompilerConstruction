package ast.statement;

import ast.AbstractSyntaxTree;

import java.util.List;

public class ProcCallNode extends AbstractSyntaxTree {
    private String m_ProcName;
    private List<AbstractSyntaxTree> m_Params;

    public ProcCallNode(String InProcName, List<AbstractSyntaxTree> InParams){
        this.m_ProcName = InProcName;
        this.m_Params = InParams;
    }
}
