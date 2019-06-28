package ast.types;

import ast.core.*;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.TypeContainer;
import writer.TypeWrapper;
import writer.TypeWrapper_Primitive;

public class PrimitiveTypeNode extends TypeNode {
    public static final PrimitiveTypeNode IntNode = new PrimitiveTypeNode(new PascalType_Int());
    public static final PrimitiveTypeNode FloatNode = new PrimitiveTypeNode(new PascalType_Float());
    public static final PrimitiveTypeNode CharNode = new PrimitiveTypeNode(new PascalType_Char());
    public static final PrimitiveTypeNode BoolNode = new PrimitiveTypeNode(new PascalType_Bool());

    private PascalType_Primitive m_PrimitiveType;

    public PrimitiveTypeNode(PascalType_Primitive InType) {
        m_PrimitiveType = InType;
    }

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

    public String GetTypeDefault() {
        return m_PrimitiveType.GetDefaultValue();
    }

    @Override
    public TypeNode CheckType() {
        return GetType();
    }

    @Override
    public TypeNode GetType() {
        return this;
    }

    @Override
    public boolean CompareType(TypeNode OtherTypeNode) {
        if (OtherTypeNode == null) {
            return false;
        }

        if (!(OtherTypeNode instanceof PrimitiveTypeNode)) {
            return false;
        }

        PascalType_Primitive OtherType = ((PrimitiveTypeNode) OtherTypeNode).m_PrimitiveType;
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
    public TypeContainer GetDefaultValue() {
        return new TypeContainer(GetWrappedType(), m_PrimitiveType.GetDefaultValue());
    }
}
