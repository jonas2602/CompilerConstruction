package ast.types;

import ast.EPrimitiveType;

public class NamedTypeNode extends TypeNode {
    private String m_Name;

    public NamedTypeNode(String InName) {
        m_Name = InName;
    }

    public NamedTypeNode(EPrimitiveType InType){
        m_Name = InType.label();
    }

    @Override
    public boolean CompareType(TypeNode OtherTypeNode) {
        // Is the given type a valid name node?
        NamedTypeNode otherNameNode = (NamedTypeNode) OtherTypeNode;
        if (otherNameNode == null) return false;

        // compare type names
        return m_Name == otherNameNode.m_Name;
    }

    // TODO: Assign Primitives to root block? How to validate them?
    // TODO: Is it a good idea that the same type objects are used multiple times in the tree?
    public static final NamedTypeNode IntNode = new NamedTypeNode(EPrimitiveType.INT);
    public static final NamedTypeNode RealNode = new NamedTypeNode(EPrimitiveType.REAL);
    public static final NamedTypeNode BoolNode = new NamedTypeNode(EPrimitiveType.BOOL);
    public static final NamedTypeNode CharNode = new NamedTypeNode(EPrimitiveType.CHAR);
    public static final NamedTypeNode StringNode = new NamedTypeNode(EPrimitiveType.STRING);
}
