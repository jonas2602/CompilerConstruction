package ast.types;

import writer.TypeWrapper;
import writer.TypeWrapper_Pointer;

public class PointerTypeNode extends TypeNode {
    public static final PointerTypeNode IntPointerNode = new PointerTypeNode(PrimitiveTypeNode.IntNode);
    public static final PointerTypeNode FloatPointerNode = new PointerTypeNode(PrimitiveTypeNode.FloatNode);
    public static final PointerTypeNode CharPointerNode = new PointerTypeNode(PrimitiveTypeNode.CharNode);
    public static final PointerTypeNode BoolPointerNode = new PointerTypeNode(PrimitiveTypeNode.BoolNode);

    protected TypeNode m_BaseType;

    public PointerTypeNode(TypeNode baseType) {
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
