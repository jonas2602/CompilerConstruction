package ast.types;

import ast.EPrimitiveType;
import ast.TypeCheckException;
import ast.declarations.TypeDeclNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Type;
import writer.GeneratorSlave;
import writer.TypeWrapper;
import writer.TypeWrapper_Other;

public class NamedTypeNode extends TypeNode {
    // TODO: Assign Primitives to root block? How to validate them?
    // TODO: Is it a good idea that the same type objects are used multiple times in the tree?
    public static final NamedTypeNode IntNode = new NamedTypeNode(EPrimitiveType.INT);
    public static final NamedTypeNode RealNode = new NamedTypeNode(EPrimitiveType.FLOAT);
    public static final NamedTypeNode BoolNode = new NamedTypeNode(EPrimitiveType.BOOL);
    public static final NamedTypeNode CharNode = new NamedTypeNode(EPrimitiveType.CHAR);
    public static final NamedTypeNode StringNode = new NamedTypeNode(EPrimitiveType.STRING);
    public static final NamedTypeNode VoidNode = new NamedTypeNode(EPrimitiveType.VOID);


    private String m_TypeName;
    // Self if primitive type, type of TypeDeclaration if custom type
    private TypeNode m_TypeDetails;

    public NamedTypeNode(String name) {
        m_TypeName = name;
    }

    public NamedTypeNode(EPrimitiveType type) {
        m_TypeName = type.label();
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        return m_TypeDetails.CompareType(otherTypeNode);
        /*if(otherTypeNode == null){
            return false;
        }

        // Is the given type a valid name node?
        if (!(otherTypeNode.GetCompareType() instanceof NamedTypeNode)) {
            return false;
        }

        // compare type names
        NamedTypeNode otherNameNode = (NamedTypeNode) otherTypeNode.GetCompareType();
        return m_TypeName == otherNameNode.m_TypeName;*/
    }

    @Override
    public TypeNode CheckType() {
        // Try get type definition from owning block
        TypeDeclNode typeDecl = GetOwningBlock().GetTypeDeclaration(m_TypeName);
        if (typeDecl != null) {
            m_TypeDetails = typeDecl.GetType();
            return GetType();
        }

        throw new TypeCheckException(this, "Type with name " + m_TypeName + " is not defined");
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_TypeDetails.GetTypeDetails();
    }

    @Override
    public TypeNode GetType() {
        if (m_TypeDetails != null) {
            return m_TypeDetails;
        }

        return this;
    }

    @Override
    public String toString() {
        return "Type(" + m_TypeName + ")";
    }

    @Override
    public TypeWrapper GetWrappedType() {
        // Handle complex types
        if (m_TypeDetails != null) {
            return m_TypeDetails.GetWrappedType();
        }

        // TODO: are there other named types with special wrapped type?
        return TypeWrapper_Other.VOID;
    }
}
