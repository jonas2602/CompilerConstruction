package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.expressions.ConstantNode;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;
import writer.wrappers.TypeWrapper_Array;
import writer.wrappers.ValueWrapper_Constant;

import java.util.Set;

public class ArrayTypeNode extends TypeNode {
    public static final ArrayTypeNode IntArrayNode = new ArrayTypeNode(Integer.MAX_VALUE, PrimitiveTypeNode.IntNode);
    public static final ArrayTypeNode FloatArrayNode = new ArrayTypeNode(Integer.MAX_VALUE, PrimitiveTypeNode.FloatNode);
    public static final ArrayTypeNode CharArrayNode = new ArrayTypeNode(Integer.MAX_VALUE, PrimitiveTypeNode.CharNode);
    public static final ArrayTypeNode BoolArrayNode = new ArrayTypeNode(Integer.MAX_VALUE, PrimitiveTypeNode.BoolNode);

    public static final ArrayTypeNode WildcardArrayNode() {
        return new ArrayTypeNode(Integer.MAX_VALUE, new WildcardTypeNode());
    }

    protected AbstractSyntaxTree m_ElementCounter;
    protected TypeNode m_ElementType;

    public ArrayTypeNode(AbstractSyntaxTree elementCounter, TypeNode elementType) {
        m_ElementCounter = elementCounter;
        m_ElementType = elementType;

        m_ElementCounter.SetParent(this);
        m_ElementType.SetParent(this);
    }

    public ArrayTypeNode(int size, TypeNode elementType) {
        m_ElementCounter = new RangeTypeNode(0, size - 1, PrimitiveTypeNode.IntNode);
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
        return m_ElementType.GetType();
    }

    @Override
    public boolean CompareType(TypeNode otherNodeType) {
        if (otherNodeType == null) {
            return false;
        }

        // is subclass of ArrayTypeNode?
        if (!(otherNodeType.GetCompareType() instanceof ArrayTypeNode)) {
            return false;
        }

        // is exactly Array type node?
        if (!(otherNodeType.GetCompareType().getClass() == ArrayTypeNode.class)) {
            return false;
        }

        TypeNode otherElementType = ((ArrayTypeNode) otherNodeType.GetCompareType()).m_ElementType;
        if (!m_ElementType.CompareType(otherElementType)) {
            return false;
        }

        // TODO: Compare Size? -1 to ignore size?
        int otherSize = ((ArrayTypeNode) otherNodeType.GetCompareType()).GetSize();
        if (otherSize == Integer.MAX_VALUE && GetSize() == Integer.MAX_VALUE) {
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

    @Override
    public ParamContainer GetDefaultValue() {
        return new ParamContainer(GetWrappedType(), ValueWrapper_Constant.ZEROINITIALIZER);
    }

    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        return m_ElementType.GetWildcards();
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new ArrayTypeNode(m_ElementCounter.Copy(), (TypeNode) m_ElementType.Copy());
    }
}
