package ast;

import ast.types.TypeNode;

public class AbstractSyntaxTree {
    private AbstractSyntaxTree m_Parent;
    private BlockNode m_OwningBlock;

    protected BlockNode GetOwningBlock() {
        if (m_OwningBlock != null) {
            return m_OwningBlock;
        }

        if (m_Parent != null) {
            BlockNode parentBlock = (BlockNode) m_Parent;
            if (parentBlock != null) {
                m_OwningBlock = parentBlock;
            } else {
                m_OwningBlock = m_Parent.GetOwningBlock();
            }
        }

        return m_OwningBlock;
    }

    protected AbstractSyntaxTree GetParent() {
        return m_Parent;
    }

    public void SetParent(AbstractSyntaxTree InParent) {
        m_Parent = InParent;
    }

    public TypeNode GetType() {
        return null;
    }

    // NULL if no type returned, throws exception if failed
    public TypeNode CheckType() {
        return null;
    }
}
