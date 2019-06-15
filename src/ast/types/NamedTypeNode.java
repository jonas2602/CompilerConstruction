package ast.types;

import ast.EPrimitiveType;
import ast.TypeCheckException;
import ast.declarations.TypeDeclNode;

public class NamedTypeNode extends TypeNode {
    // TODO: Assign Primitives to root block? How to validate them?
    // TODO: Is it a good idea that the same type objects are used multiple times in the tree?
    public static final NamedTypeNode IntNode = new NamedTypeNode(EPrimitiveType.INT);
    public static final NamedTypeNode RealNode = new NamedTypeNode(EPrimitiveType.REAL);
    public static final NamedTypeNode BoolNode = new NamedTypeNode(EPrimitiveType.BOOL);
    public static final NamedTypeNode CharNode = new NamedTypeNode(EPrimitiveType.CHAR);
    public static final NamedTypeNode StringNode = new NamedTypeNode(EPrimitiveType.STRING);


    private String m_Name;
    // Self if primitive type, type of TypeDeclaration if custom type
    private TypeNode m_CachedActualType;

    public NamedTypeNode(String InName) {
        m_Name = InName;
    }

    public NamedTypeNode(EPrimitiveType InType) {
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

    @Override
    public TypeNode CheckType() {
        // Is primitive Type?
        if (IntNode.CompareType(this) || RealNode.CompareType(this) || BoolNode.CompareType(this) || CharNode.CompareType(this) || StringNode.CompareType(this)) {
            m_CachedActualType = this;
            return m_CachedActualType;
        }

        // is defined type
        TypeDeclNode typeDecl = GetOwningBlock().GetTypeDeclaration(m_Name);
        if (typeDecl != null) {
            m_CachedActualType = typeDecl.GetType();
            return m_CachedActualType;
        }

        throw new TypeCheckException(this, "Type with name " + m_Name + " is not defined");
    }

    @Override
    public TypeNode GetType() {
        return m_CachedActualType;
    }

    @Override
    public String toString() {
        return "Type(" + m_Name + ")";
    }
}
