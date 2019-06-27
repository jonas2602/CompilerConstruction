package ast.core;

import writer.TypeManager;

public class PascalType_Array extends PascalType {
    private PascalType m_BaseType;
    private int m_Size;

    public PascalType_Array(PascalType InBaseType, int InSize) {
        m_BaseType = InBaseType;
        m_Size = InSize;
    }

    @Override
    public String GetTypeName() {
        String baseTypeName = m_BaseType.GetTypeName();
        return TypeManager.MakeArrayType(baseTypeName, m_Size);
    }
}
