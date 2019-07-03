package ast.types;

import writer.TypeWrapper;
import writer.TypeWrapper_Pointer;

public class PointerTypeNode extends TypeNode {
    public static final PointerTypeNode IntPointerNode = new PointerTypeNode(PrimitiveTypeNode.IntNode);
    public static final PointerTypeNode FloatPointerNode = new PointerTypeNode(PrimitiveTypeNode.FloatNode);
    public static final PointerTypeNode CharPointerNode = new PointerTypeNode(PrimitiveTypeNode.CharNode);
    public static final PointerTypeNode BoolPointerNode = new PointerTypeNode(PrimitiveTypeNode.BoolNode);

    public static final PointerTypeNode ConstIntPointerNode = new PointerTypeNode(PrimitiveTypeNode.IntNode, true);
    public static final PointerTypeNode ConstFloatPointerNode = new PointerTypeNode(PrimitiveTypeNode.FloatNode, true);
    public static final PointerTypeNode ConstCharPointerNode = new PointerTypeNode(PrimitiveTypeNode.CharNode, true);
    public static final PointerTypeNode ConstBoolPointerNode = new PointerTypeNode(PrimitiveTypeNode.BoolNode, true);

    protected TypeNode m_BaseType;

    public PointerTypeNode(TypeNode baseType) {
        this(baseType, false);
    }

    public PointerTypeNode(TypeNode baseType, boolean bConstant) {
        super(bConstant);
        m_BaseType = baseType;
    }

    @Override
    public TypeNode CheckType() {
        // TODO: which types are allowed to point at?
        m_BaseType.CheckType();
        return GetType();
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_BaseType;
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof PointerTypeNode)) {
            return false;
        }

        TypeNode OtherBaseType = ((PointerTypeNode) otherTypeNode.GetCompareType()).m_BaseType;
        if (!OtherBaseType.CompareType(m_BaseType)) {
            return false;
        }

        return true;
    }

    @Override
    public TypeWrapper GetWrappedType() {
        return new TypeWrapper_Pointer(m_BaseType.GetWrappedType());
    }
}
