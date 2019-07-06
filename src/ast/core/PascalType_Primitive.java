package ast.core;

import ast.EPrimitiveType;
import writer.TypeManager;

public enum PascalType_Primitive implements PascalType {
    LONG(EPrimitiveType.LONG, 64, false, "%l", "0"),
    INT(EPrimitiveType.INT, 32, false, "%d", "0"),
    FLOAT(EPrimitiveType.FLOAT, 32, true, "%f", "0.0"),
    DOUBLE(EPrimitiveType.DOUBLE, 64, true, "%f", "0.0"),
    CHAR(EPrimitiveType.CHAR, 8, false, "%c", "0"),
    BOOL(EPrimitiveType.BOOL, 1, false, "%d", "0");

    private EPrimitiveType m_Type;
    private int m_Size;
    private boolean m_bDezimal;
    private String m_Placeholder;
    private String m_DefaultValue;

    PascalType_Primitive(EPrimitiveType type, int size, boolean dezimal, String placeholder, String defaultValue) {
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
