package ast.core;

import ast.EPrimitiveType;
import writer.TypeManager;

public class PascalType_Primitive extends PascalType {
    private EPrimitiveType m_Type;
    private int m_Size;
    private boolean m_bDezimal;
    private String m_Placeholder;
    private String m_DefaultValue;

    public PascalType_Primitive(EPrimitiveType type, int size, boolean dezimal, String placeholder, String defaultValue) {
        m_Type = type;
        m_Size = size;
        m_bDezimal = dezimal;
        m_Placeholder = placeholder;
        m_DefaultValue = defaultValue;
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
