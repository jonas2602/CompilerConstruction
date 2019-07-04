package ast.types;

import writer.TypeWrapper;
import writer.TypeWrapper_Pointer;

public class PointerTypeNode extends TypeNode {
    public static final PointerTypeNode IntPointerNode = new PointerTypeNode(PrimitiveTypeNode.IntNode);
    public static final PointerTypeNode FloatPointerNode = new PointerTypeNode(PrimitiveTypeNode.FloatNode);
    public static final PointerTypeNode CharPointerNode = new PointerTypeNode(PrimitiveTypeNode.CharNode);
    public static final PointerTypeNode BoolPointerNode = new PointerTypeNode(PrimitiveTypeNode.BoolNode);

    public static final PointerTypeNode ConstIntPointerNode = new PointerTypeNode(PrimitiveTypeNode.ConstIntNode);
    public static final PointerTypeNode ConstFloatPointerNode = new PointerTypeNode(PrimitiveTypeNode.ConstFloatNode);
    public static final PointerTypeNode ConstCharPointerNode = new PointerTypeNode(PrimitiveTypeNode.ConstCharNode);
    public static final PointerTypeNode ConstBoolPointerNode = new PointerTypeNode(PrimitiveTypeNode.ConstBoolNode);

    protected TypeNode m_BaseType;

    public PointerTypeNode(TypeNode baseType) {
        this(baseType, false);
    }

    public PointerTypeNode(TypeNode baseType, boolean bConstant) {
        super(bConstant);
        m_BaseType = baseType;
        m_BaseType.SetParent(this);
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

        // only assign ptr to ptr
        if (!(otherTypeNode.GetCompareType() instanceof PointerTypeNode)) {
            return false;
        }

        // Assigned value has fitting type?
        TypeNode otherBaseType = ((PointerTypeNode) otherTypeNode.GetCompareType()).m_BaseType;
        if (!otherBaseType.CompareType(m_BaseType)) {
            return false;
        }

        return true;
    }

    @Override
    public TypeWrapper GetWrappedType() {
        return new TypeWrapper_Pointer(m_BaseType.GetWrappedType());
    }
}
