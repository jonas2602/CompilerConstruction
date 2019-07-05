package ast.types;

import ast.core.*;
import writer.snippets.CodeSnippet_Base;
import writer.snippets.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;
import writer.wrapper.TypeWrapper;
import writer.wrapper.TypeWrapper_Primitive;

public class PrimitiveTypeNode extends TypeNode {
    public static final PrimitiveTypeNode IntNode = new PrimitiveTypeNode(new PascalType_Int());
    public static final PrimitiveTypeNode FloatNode = new PrimitiveTypeNode(new PascalType_Float());
    public static final PrimitiveTypeNode DoubleNode = new PrimitiveTypeNode(new PascalType_Double());
    public static final PrimitiveTypeNode CharNode = new PrimitiveTypeNode(new PascalType_Char());
    public static final PrimitiveTypeNode BoolNode = new PrimitiveTypeNode(new PascalType_Bool());

    public static final PrimitiveTypeNode ConstIntNode = new PrimitiveTypeNode(new PascalType_Int(), true);
    public static final PrimitiveTypeNode ConstFloatNode = new PrimitiveTypeNode(new PascalType_Float(), true);
    public static final PrimitiveTypeNode ConstDoubleNode = new PrimitiveTypeNode(new PascalType_Double(), true);
    public static final PrimitiveTypeNode ConstCharNode = new PrimitiveTypeNode(new PascalType_Char(), true);
    public static final PrimitiveTypeNode ConstBoolNode = new PrimitiveTypeNode(new PascalType_Bool(), true);


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

    public String GetTypeShortName() {
        return m_PrimitiveType.GetTypeName();
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
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof PrimitiveTypeNode)) {
            return false;
        }

        PascalType_Primitive OtherType = ((PrimitiveTypeNode) otherTypeNode.GetCompareType()).m_PrimitiveType;
        if (OtherType.getClass() != m_PrimitiveType.getClass()) {
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
}
