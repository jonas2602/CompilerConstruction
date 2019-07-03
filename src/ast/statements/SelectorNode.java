package ast.statements;

import ast.AbstractSyntaxTree;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.ValueWrapper;

import java.util.ArrayList;
import java.util.List;

public class SelectorNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_CompStmt;
    private List<Integer> m_Selectors;

    public SelectorNode(AbstractSyntaxTree compstmt) {
        m_CompStmt = compstmt;
        m_Selectors = new ArrayList<>();

        m_CompStmt.SetParent(this);
    }

    public AbstractSyntaxTree GetCompStmt() {
        return m_CompStmt;
    }

    public List<Integer> GetSelectors() {
        return m_Selectors;
    }

    public void AddSelector(int selector) {
        m_Selectors.add(selector);
    }

    @Override
    public TypeNode CheckType() {
        m_CompStmt.CheckType();
        return null;
    }
}
