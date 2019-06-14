package ast.expression;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class VariableNode extends AbstractSyntaxTree {
    private String m_Name;
    private AbstractSyntaxTree m_Declaration;

    public VariableNode(String InName) {
        this.m_Name = InName;
    }

    @Override
    public TypeNode CheckType() {
        return GetType();
    }

    @Override
    public TypeNode GetType() {
        if(m_Declaration == null){
            // TODO: get declaration
            m_Declaration = GetOwningBlock();
        }

        return m_Declaration.GetType();
    }
}
