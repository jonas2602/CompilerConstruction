package ast.statements;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

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

    @Override
    public TypeNode CheckType() {
        for (AbstractSyntaxTree stmt : m_Statements) {
            stmt.CheckType();
        }

        return null;
    }
}
