package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.expressions.ConstantNode;
import writer.TypeManager;
import writer.TypeWrapper;
import writer.TypeWrapper_Array;

public class ArrayTypeNode extends TypeNode {
    public static final ArrayTypeNode IntArrayNode = new ArrayTypeNode(0, PrimitiveTypeNode.IntNode);
    public static final ArrayTypeNode FloatArrayNode = new ArrayTypeNode(0, PrimitiveTypeNode.FloatNode);
    public static final ArrayTypeNode CharArrayNode = new ArrayTypeNode(0, PrimitiveTypeNode.CharNode);
    public static final ArrayTypeNode BoolArrayNode = new ArrayTypeNode(0, PrimitiveTypeNode.BoolNode);

    private AbstractSyntaxTree m_ElementCounter;
    private TypeNode m_ElementType;

    public ArrayTypeNode(AbstractSyntaxTree InElementCounter, TypeNode InElementType) {
        m_ElementCounter = InElementCounter;
        m_ElementType = InElementType;

        m_ElementCounter.SetParent(this);
        m_ElementType.SetParent(this);
    }

    public ArrayTypeNode(int InSize, TypeNode InElementType) {
        ConstantNode min = new ConstantNode("0", PrimitiveTypeNode.IntNode);
        ConstantNode max = new ConstantNode(Integer.toString(InSize - 1), PrimitiveTypeNode.IntNode);
        m_ElementCounter = new RangeTypeNode(min, max);
        m_ElementType = InElementType;

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
    public TypeNode GetType() {
        return this;
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_ElementType;
    }

    @Override
    public boolean CompareType(TypeNode OtherTypeNode) {
        if (OtherTypeNode == null) {
            return false;
        }

        if (!(OtherTypeNode instanceof ArrayTypeNode)) {
            return false;
        }

        TypeNode OtherElementType = ((ArrayTypeNode) OtherTypeNode).m_ElementType;
        if (!OtherElementType.CompareType(m_ElementType)) {
            return false;
        }

        // TODO: Compare Size?

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
