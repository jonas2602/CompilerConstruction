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


    private String m_TypeName;
    // Self if primitive type, type of TypeDeclaration if custom type
    private TypeNode m_TypeDetails;

    public NamedTypeNode(String InName) {
        m_TypeName = InName;
    }

    public NamedTypeNode(EPrimitiveType InType) {
        m_TypeName = InType.label();
    }

    @Override
    public boolean CompareType(TypeNode OtherTypeNode) {
        TypeNode otherCompareType = OtherTypeNode.GetCompareType();

        // Is the given type a valid name node?
        if (!(otherCompareType instanceof NamedTypeNode)) return false;
        NamedTypeNode otherNameNode = (NamedTypeNode) otherCompareType;

        // compare type names
        return m_TypeName == otherNameNode.m_TypeName;
    }

    @Override
    public TypeNode CheckType() {
        // Is primitive Type?
        if (IntNode.CompareType(this) || RealNode.CompareType(this) || BoolNode.CompareType(this) || CharNode.CompareType(this) || StringNode.CompareType(this)) {
            m_TypeDetails = this;
            return GetType();
        }

        // is defined type
        TypeDeclNode typeDecl = GetOwningBlock().GetTypeDeclaration(m_TypeName);
        if (typeDecl != null) {
            m_TypeDetails = typeDecl.GetType();
            return GetType();
        }

        throw new TypeCheckException(this, "Type with name " + m_TypeName + " is not defined");
    }

    @Override
    public TypeNode GetType() {
        return this;
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_TypeDetails;
    }

    @Override
    public String toString() {
        return "Type(" + m_TypeName + ")";
    }
}
