package ast.types;

import ast.EPrimitiveType;

public class NamedTypeNode extends TypeNode {
    private String m_Name;

    public NamedTypeNode(String InName) {
        m_Name = InName;
    }

    public NamedTypeNode(EPrimitiveType InType){
        m_Name = InType.name();
    }

    @Override
    public boolean CompareType(TypeNode OtherTypeNode) {
        // Is the given type a valid name node?
        NamedTypeNode otherNameNode = (NamedTypeNode) OtherTypeNode;
        if (otherNameNode == null) return false;

        // compare type names
        return m_Name == otherNameNode.m_Name;
    }

    public static NamedTypeNode IntNode() { return new NamedTypeNode((EPrimitiveType.INT)); }
    public static NamedTypeNode RealNode() { return new NamedTypeNode((EPrimitiveType.REAL)); }
    public static NamedTypeNode BoolNode() { return new NamedTypeNode((EPrimitiveType.BOOL)); }
    public static NamedTypeNode CharNode() { return new NamedTypeNode((EPrimitiveType.CHAR)); }
    public static NamedTypeNode StringNode() { return new NamedTypeNode((EPrimitiveType.STRING)); }
}
