package ast.types;

import ast.AbstractSyntaxTree;

import java.util.Set;

public class ArrayTypeNode_Dynamic extends RecordTypeNode {
    public static final ArrayTypeNode_Dynamic WildcardArrayNode() {
        return new ArrayTypeNode_Dynamic(new WildcardTypeNode());
    }

    // screams for an enum
    public static final String DynamicLengthName = "length";
    public static final int DynamicLengthIndex = 0;
    public static final String DynamicStartName = "start";
    public static final int DynamicStartIndex = 1;

    public static String CreateDynamicArrayName(TypeNode baseType) {
        return "arr" + baseType.toString();
    }

    private TypeNode m_ElementType;

    public ArrayTypeNode_Dynamic(TypeNode elementType) {
        m_ElementType = elementType;
        m_ElementType.SetParent(this);
        AddEntry(DynamicLengthName, PrimitiveTypeNode.IntNode);
        AddEntry(DynamicStartName, new PointerTypeNode(m_ElementType));
    }

    @Override
    public String GetTypeName() {
        if (m_RecordName.isEmpty()) {
            return CreateDynamicArrayName(m_ElementType);
        }

        return m_RecordName;
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_ElementType.GetType();
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof ArrayTypeNode_Dynamic)) {
            return false;
        }

        TypeNode otherElementType = ((ArrayTypeNode_Dynamic) otherTypeNode.GetCompareType()).m_ElementType;
        if (!m_ElementType.CompareType(otherElementType)) {
            return false;
        }

        return true;
    }

    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        return m_ElementType.GetWildcards();
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new ArrayTypeNode_Dynamic((TypeNode) m_ElementType.Copy());
    }
}
