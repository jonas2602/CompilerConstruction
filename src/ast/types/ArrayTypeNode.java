package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.expressions.ConstantNode;
import writer.wrapper.TypeWrapper;
import writer.wrapper.TypeWrapper_Array;

public class ArrayTypeNode extends TypeNode {
    public static final ArrayTypeNode IntArrayNode = new ArrayTypeNode(-1, PrimitiveTypeNode.IntNode);
    public static final ArrayTypeNode FloatArrayNode = new ArrayTypeNode(-1, PrimitiveTypeNode.FloatNode);
    public static final ArrayTypeNode CharArrayNode = new ArrayTypeNode(-1, PrimitiveTypeNode.CharNode);
    public static final ArrayTypeNode BoolArrayNode = new ArrayTypeNode(-1, PrimitiveTypeNode.BoolNode);

    private AbstractSyntaxTree m_ElementCounter;
    private TypeNode m_ElementType;

    public ArrayTypeNode(AbstractSyntaxTree elementCounter, TypeNode elementType) {
        m_ElementCounter = elementCounter;
        m_ElementType = elementType;

        m_ElementCounter.SetParent(this);
        m_ElementType.SetParent(this);
    }

    public ArrayTypeNode(int size, TypeNode elementType) {
        ConstantNode min = new ConstantNode("0", PrimitiveTypeNode.IntNode);
        ConstantNode max = new ConstantNode(Integer.toString(size - 1), PrimitiveTypeNode.IntNode);
        m_ElementCounter = new RangeTypeNode(min, max);
        m_ElementType = elementType;

        m_ElementCounter.SetParent(this);
        m_ElementType.SetParent(this);
    }

    public int GetSize() {
        return ((RangeTypeNode) m_ElementCounter).GetRangeSize();
    }

    @Override
    public TypeNode CheckType() {
        m_ElementType.CheckType();

        TypeNode counterType = m_ElementCounter.CheckType();
        if (!(counterType instanceof RangeTypeNode)) {
            throw new TypeCheckException(this, "Element Count of an array must be defined by a range");
        }

        return GetType();
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_ElementType;
    }

    @Override
    public boolean CompareType(TypeNode otherNodeType) {
        if (otherNodeType == null) {
            return false;
        }

        if (!(otherNodeType.GetCompareType() instanceof ArrayTypeNode)) {
            return false;
        }

        TypeNode otherElementType = ((ArrayTypeNode) otherNodeType.GetCompareType()).m_ElementType;
        if (!otherElementType.CompareType(m_ElementType)) {
            return false;
        }

        // TODO: Compare Size? -1 to ignore size?
        int otherSize = ((ArrayTypeNode) otherNodeType.GetCompareType()).GetSize();
        if (otherSize < 0 && GetSize() < 0) {
            if (otherSize != GetSize()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public TypeWrapper GetWrappedType() {
        if (!(m_ElementCounter instanceof RangeTypeNode)) {
            System.out.println("Array only supports ranged definitions right now");
        }

        return new TypeWrapper_Array(m_ElementType.GetWrappedType(), GetSize());
    }
}
