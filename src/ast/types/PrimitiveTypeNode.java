package ast.types;

import ast.AbstractSyntaxTree;
import ast.core.*;
import writer.snippets.CodeSnippet_Base;
import writer.snippets.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;
import writer.wrappers.TypeWrapper_Primitive;

public class PrimitiveTypeNode extends TypeNode {
    public static final PrimitiveTypeNode IntNode = new PrimitiveTypeNode(PascalType_Primitive.INT);
    public static final PrimitiveTypeNode FloatNode = new PrimitiveTypeNode(PascalType_Primitive.FLOAT);
    public static final PrimitiveTypeNode DoubleNode = new PrimitiveTypeNode(PascalType_Primitive.DOUBLE);
    public static final PrimitiveTypeNode CharNode = new PrimitiveTypeNode(PascalType_Primitive.CHAR);
    public static final PrimitiveTypeNode BoolNode = new PrimitiveTypeNode(PascalType_Primitive.BOOL);

    public static final PrimitiveTypeNode ConstIntNode = new PrimitiveTypeNode(PascalType_Primitive.INT, true);
    public static final PrimitiveTypeNode ConstFloatNode = new PrimitiveTypeNode(PascalType_Primitive.FLOAT, true);
    public static final PrimitiveTypeNode ConstDoubleNode = new PrimitiveTypeNode(PascalType_Primitive.DOUBLE, true);
    public static final PrimitiveTypeNode ConstCharNode = new PrimitiveTypeNode(PascalType_Primitive.CHAR, true);
    public static final PrimitiveTypeNode ConstBoolNode = new PrimitiveTypeNode(PascalType_Primitive.BOOL, true);

    public static final WildcardTypeNode WildCardPrimitiveNode = new WildcardTypeNode(PrimitiveTypeNode.class);

    private PascalType_Primitive m_PrimitiveType;

    public PrimitiveTypeNode(PascalType_Primitive type) {
        this(type, false);
    }

    public PrimitiveTypeNode(PascalType_Primitive type, boolean bConstant) {
        super(bConstant);
        m_PrimitiveType = type;
    }


    @Override
    public String GetTypePlaceholder() {
        return m_PrimitiveType.GetPlaceholder();
    }

    @Override
    public String GetShortName() {
        return m_PrimitiveType.GetShortName();
    }

    public int GetTypeSize() {
        return m_PrimitiveType.GetSize();
    }

    public boolean GetTypeIsDezimal() {
        return m_PrimitiveType.GetIsDezimal();
    }

    @Override
    public TypeNode CheckType() {
        return GetType();
    }


    @Override
    public String toString() {
        return m_PrimitiveType.toString();
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof PrimitiveTypeNode)) {
            return false;
        }

        PascalType_Primitive OtherType = ((PrimitiveTypeNode) otherTypeNode.GetCompareType()).m_PrimitiveType;
        if (OtherType != m_PrimitiveType) {
            return false;
        }

        return true;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return new CodeSnippet_Plain(m_PrimitiveType.GetTypeName());
    }

    @Override
    public TypeWrapper GetWrappedType() {
        return new TypeWrapper_Primitive(m_PrimitiveType);
    }

    @Override
    public ParamContainer GetDefaultValue() {
        return new ParamContainer(GetWrappedType(), m_PrimitiveType.GetDefaultValue());
    }

    @Override
    public void InitVariable(GeneratorSlave slave, ParamContainer varParam) {
        slave.StoreInVariable(varParam, GetDefaultValue());
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new PrimitiveTypeNode(m_PrimitiveType, m_bConstant);
    }
}
