package ast.statement;

import ast.AbstractSyntaxTree;

import java.util.ArrayList;
import java.util.List;

public class CompStmtNode extends AbstractSyntaxTree {
    private List<AbstractSyntaxTree> m_Statements = new ArrayList<>();

    public CompStmtNode() {

    }

    public void AddStatement(AbstractSyntaxTree stmt) {
        m_Statements.add(stmt);
        stmt.SetParent(this);
    }
}
