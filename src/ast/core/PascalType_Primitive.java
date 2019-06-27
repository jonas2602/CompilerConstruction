package ast.core;

import ast.EPrimitiveType;
import writer.TypeManager;

public class PascalType_Primitive extends PascalType {
    private EPrimitiveType m_Type;
    private int m_Size;
    private boolean m_bDezimal;
    private String m_Placeholder;
    private String m_DefaultValue;

    public PascalType_Primitive(EPrimitiveType InType, int InSize, boolean InDezimal, String InPlaceholder, String InDefaultValue) {
        m_Type = InType;
        m_Size = InSize;
        m_bDezimal = InDezimal;
        m_Placeholder = InPlaceholder;
        m_DefaultValue = InDefaultValue;
    }

    @Override
    public String GetTypeName()  {
        return TypeManager.GetTypeName(m_Type);
    }

    public int GetSize() {
        return m_Size;
    }

    public boolean GetIsDezimal() {
        return m_bDezimal;
    }

    public String GetPlaceholder() {
        return m_Placeholder;
    }

    public String GetDefaultValue() {
        return m_DefaultValue;
    }
}
