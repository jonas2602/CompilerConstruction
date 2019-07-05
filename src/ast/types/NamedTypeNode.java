package ast.types;

import ast.TypeCheckException;
import ast.declarations.TypeDeclNode;
import writer.wrapper.TypeWrapper;

public class NamedTypeNode extends TypeNode {
    private String m_TypeName;
    private TypeNode m_TypeDetails;

    public NamedTypeNode(String name) {
        m_TypeName = name;
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        return m_TypeDetails.CompareType(otherTypeNode);
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
        // if (m_TypeDetails != null) {
            return m_TypeDetails.GetTypeDetails();
        // } else {
        //     return this;
        // }
    }

    @Override
    public TypeNode GetType() {
        // if (m_TypeDetails != null) {
            return m_TypeDetails;
        // }

        // return this;
    }

    @Override
    public String toString() {
        return "Type(" + m_TypeName + ")";
    }

    @Override
    public TypeWrapper GetWrappedType() {
        // if (m_TypeDetails != null) {
            return m_TypeDetails.GetWrappedType();
        // }

        // return null;
    }
}
