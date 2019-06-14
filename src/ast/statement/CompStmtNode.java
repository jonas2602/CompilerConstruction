package ast.statement;

import ast.AbstractSyntaxTree;

public class CompStmtNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree[] m_Statements;

    public CompStmtNode(AbstractSyntaxTree[] InStatements) {
        this.m_Statements = InStatements;
    }
}
