package ast.statements;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.ArrayList;
import java.util.List;

public class CompStmtNode extends AbstractSyntaxTree {
    private List<AbstractSyntaxTree> m_Statements;

    public CompStmtNode() {
        m_Statements = new ArrayList<>();
    }

    public CompStmtNode(AbstractSyntaxTree... elements) {
        m_Statements = new ArrayList<>();
        for (AbstractSyntaxTree e : elements) {
            AddStatement(e);
        }
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

        return VoidTypeNode.VoidNode;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        for (AbstractSyntaxTree node : m_Statements) {
            node.CreateSnippet(slave);
        }

        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        for (AbstractSyntaxTree node : m_Statements) {
            node.CreateSnippet(slave);
        }

        return ParamContainer.VOIDCONTAINER();
    }
}
