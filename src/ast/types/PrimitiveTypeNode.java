package ast.types;

import ast.core.PascalType;
import ast.core.PascalType_Char;
import ast.core.PascalType_Float;
import ast.core.PascalType_Int;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;

public class PrimitiveTypeNode extends TypeNode {
    public static final PrimitiveTypeNode IntNode = new PrimitiveTypeNode(new PascalType_Int());
    public static final PrimitiveTypeNode FloatNode = new PrimitiveTypeNode(new PascalType_Float());
    public static final PrimitiveTypeNode CharNode = new PrimitiveTypeNode(new PascalType_Char());
    // public static final NamedTypeNode BoolNode = new NamedTypeNode(EPrimitiveType.BOOL);
    // public static final NamedTypeNode StringNode = new NamedTypeNode(EPrimitiveType.STRING);
    // public static final NamedTypeNode VoidNode = new NamedTypeNode(EPrimitiveType.VOID);

    private PascalType m_PrimitiveType;

    public PrimitiveTypeNode(PascalType InType) {
        m_PrimitiveType = InType;
    }

    public String GetTypePlaceholder() {
        return m_PrimitiveType.GetPlaceholder();
    }

    public String GetTypeShortName() {
        return m_PrimitiveType.GetShortName();
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

        PascalType OtherType = ((PrimitiveTypeNode) OtherTypeNode).m_PrimitiveType;
        if (OtherType.getClass() != m_PrimitiveType.getClass()) {
            return false;
        }

        return true;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return new CodeSnippet_Plain(m_PrimitiveType.GetShortName());
    }
}
