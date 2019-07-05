package ast.statements;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

import java.util.ArrayList;
import java.util.List;

public class CompStmtNode extends AbstractSyntaxTree {
    private List<AbstractSyntaxTree> m_Statements;

    public CompStmtNode() {
        m_Statements = new ArrayList<>();
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

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        for(AbstractSyntaxTree node : m_Statements){
            // node.CreateSnippet(slave, ctx);
            node.CreateSnippet(slave);
            // slave.GetScopeSnippet().AddStatement(stmt);
        }

        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        for(AbstractSyntaxTree node : m_Statements){
            // node.CreateSnippet(slave, ctx);
            node.CreateSnippet(slave);
            // slave.GetScopeSnippet().AddStatement(stmt);
        }

        return null;
    }
}
