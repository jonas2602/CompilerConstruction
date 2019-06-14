package ast.expression;

import ast.AbstractSyntaxTree;
import ast.declaration.VarDeclNode;
import ast.types.TypeNode;

public class VariableNode extends AbstractSyntaxTree {
    private String m_Name;
    private VarDeclNode m_Declaration;

    public VariableNode(String InName) {
        this.m_Name = InName;
    }

    @Override
    public TypeNode CheckType() {
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
