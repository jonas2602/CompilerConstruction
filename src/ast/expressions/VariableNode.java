package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.declarations.VarDeclNode;
import ast.types.TypeNode;

public class VariableNode extends AbstractSyntaxTree {
    private String m_Name;
    private VarDeclNode m_Declaration;

    public VariableNode(String InName) {
        this.m_Name = InName;
    }

    @Override
    public TypeNode CheckType() {
        // Variable Declaration should already be checked by the owning Block
        // -> only return the Declaration Type
        return GetType();
    }

    @Override
    public TypeNode GetType() {
        if (m_Declaration == null) {
            m_Declaration = GetOwningBlock().GetVariableDeclaration(m_Name);
        }

        return m_Declaration.GetType();
    }
}
