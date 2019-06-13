package ast.types;

import ast.AbstractSyntaxTree;

public class NamedTypeNode extends AbstractSyntaxTree {
    private String m_Name;

    public NamedTypeNode(String InName) {
        m_Name = InName;
    }
}
