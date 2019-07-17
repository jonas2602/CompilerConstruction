package ast.types;

import ast.AbstractSyntaxTree;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;
import writer.wrappers.TypeWrapper_Pointer;
import writer.wrappers.ValueWrapper_Constant;

import java.util.Set;

public class PointerTypeNode extends TypeNode {
    public static final PointerTypeNode IntPointerNode = new PointerTypeNode(PrimitiveTypeNode.IntNode);
    public static final PointerTypeNode FloatPointerNode = new PointerTypeNode(PrimitiveTypeNode.FloatNode);
    public static final PointerTypeNode CharPointerNode = new PointerTypeNode(PrimitiveTypeNode.CharNode);
    public static final PointerTypeNode BoolPointerNode = new PointerTypeNode(PrimitiveTypeNode.BoolNode);

    public static final PointerTypeNode ConstIntPointerNode = new PointerTypeNode(PrimitiveTypeNode.ConstIntNode);
    public static final PointerTypeNode ConstFloatPointerNode = new PointerTypeNode(PrimitiveTypeNode.ConstFloatNode);
    public static final PointerTypeNode ConstCharPointerNode = new PointerTypeNode(PrimitiveTypeNode.ConstCharNode);
    public static final PointerTypeNode ConstBoolPointerNode = new PointerTypeNode(PrimitiveTypeNode.ConstBoolNode);

    public static final PointerTypeNode VoidPointerNode = new PointerTypeNode(VoidTypeNode.VoidNode);

    public static final PointerTypeNode WildCardPointerNode() {
        return new PointerTypeNode(new WildcardTypeNode());
    }


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
        return m_BaseType.GetType();
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

        // .. or null
        if (otherTypeNode.GetCompareType() instanceof NilTypeNode) {
            return true;
        }

        // Assigned value has fitting type?
        TypeNode otherBaseType = ((PointerTypeNode) otherTypeNode.GetCompareType()).m_BaseType;
        if (!m_BaseType.CompareType(otherBaseType)) {
            return false;
        }

        return true;
    }

    @Override
    public String GetShortName() {
        return "p" + m_BaseType.GetShortName();
    }

    @Override
    public TypeWrapper GetWrappedType() {
        return new TypeWrapper_Pointer(m_BaseType.GetWrappedType());
    }

    @Override
    public ParamContainer GetDefaultValue() {
        return new ParamContainer(GetWrappedType(), ValueWrapper_Constant.NULL);
    }

    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        return m_BaseType.GetWildcards();
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new PointerTypeNode(m_BaseType, m_bConstant);
    }
}
