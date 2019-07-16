package ast;

import ast.types.TypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public abstract class AbstractSyntaxTree {
    private AbstractSyntaxTree m_Parent;
    private BlockNode m_OwningBlock;

    protected BlockNode GetOwningBlock() {
        return GetOwningBlock(true);
    }


    protected BlockNode GetOwningBlock(boolean allowCache) {
        if (m_OwningBlock != null && allowCache) {
            return m_OwningBlock;
        }

        if (m_Parent != null) {
            if (m_Parent instanceof BlockNode) {
                m_OwningBlock = (BlockNode) m_Parent;
            } else {
                m_OwningBlock = m_Parent.GetOwningBlock();
            }
        }

        return m_OwningBlock;
    }

    public AbstractSyntaxTree GetParent() {
        return m_Parent;
    }

    public void SetParent(AbstractSyntaxTree parent) {
        m_Parent = parent;
    }

    public TypeNode GetType() {
        return null;
    }

    // NULL if no type returned, throws exception if failed
    public TypeNode CheckType() {
        return null;
    }
    // TODO: split type checking in TryCheckType() and CheckType_Impl()
    //  TryCheck will be called from parent and calls the actual implementation only if a local variable (bChecked)
    //  is false, to avoid duplicate checks.
    //  CheckType_Impl replaces the current function and gets inherited by all Nodes.

    public AbstractSyntaxTree Copy() {
        return null;
    }

    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return null;
    }

    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return null;
    }
}
