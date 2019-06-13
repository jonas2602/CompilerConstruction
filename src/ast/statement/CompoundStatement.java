package ast.statement;

import ast.AbstractSyntaxTree;

public class CompoundStatement extends AbstractSyntaxTree {
    private AbstractSyntaxTree[] m_Statements;

    public CompoundStatement(AbstractSyntaxTree[] InStatements) {
        this.m_Statements = InStatements;
    }
}
