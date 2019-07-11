package ast.statements;

import ast.AbstractSyntaxTree;
import ast.declarations.ConstDeclNode;
import ast.expressions.AccessNode_Variable;
import ast.types.TypeNode;

import java.util.ArrayList;
import java.util.List;

public class SelectorNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_CompStmt;
    private List<AbstractSyntaxTree> m_Selectors;

    public SelectorNode(AbstractSyntaxTree compstmt) {
        m_CompStmt = compstmt;
        m_Selectors = new ArrayList<>();

        m_CompStmt.SetParent(this);
    }

    public AbstractSyntaxTree GetCompStmt() {
        return m_CompStmt;
    }

    public List<AbstractSyntaxTree> GetSelectors() {
        return m_Selectors;
    }

    public void AddSelector(AbstractSyntaxTree selector) {
        m_Selectors.add(selector);
        selector.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        m_CompStmt.CheckType();

        TypeNode type = m_Selectors.get(0).CheckType();
        for (AbstractSyntaxTree selc: m_Selectors) {
            if (!type.CompareType(selc.CheckType())) {
                throw new RuntimeException("Typecheck failed at Selector Node because of type missmatch!");
            }

            //check for const
            if (selc instanceof AccessNode_Variable) {
                AccessNode_Variable av = (AccessNode_Variable) selc;
                if (!(av.GetDeclaration() instanceof ConstDeclNode)) {
                    throw new RuntimeException("Typecheck failed at Selector Node because only constants are supported!");
                }
            }
        }

        return type;
    }
}
