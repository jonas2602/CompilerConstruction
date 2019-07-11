package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.TypeDeclNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

import java.util.HashSet;
import java.util.Set;

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
        return m_TypeDetails.GetTypeDetails();
    }

    @Override
    public TypeNode GetType() {
        return m_TypeDetails.GetType();
    }

    @Override
    public String toString() {
        return m_TypeName;
    }

    @Override
    public TypeWrapper GetWrappedType() {
        // if (m_TypeDetails != null) {
        return m_TypeDetails.GetWrappedType();
        // }

        // return null;
    }

    @Override
    public void InitVariable(GeneratorSlave slave, ParamContainer varParam) {
        m_TypeDetails.InitVariable(slave, varParam);
    }


    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        if (m_TypeDetails != null) {
            return m_TypeDetails.GetWildcards();
        }

        return new HashSet<>();
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new NamedTypeNode(m_TypeName);
    }
}
