package ast;

import ast.types.TypeNode;

public class AbstractSyntaxTree {
    protected AbstractSyntaxTree m_Parent;
    protected BlockNode m_OwningBlock;

    public BlockNode GetOwningBlock() {
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

    public TypeNode GetType() {
        return null;
    }

    // NULL if no type returned, throws exception if failed
    public TypeNode CheckType() {
        return null;
    }
}
