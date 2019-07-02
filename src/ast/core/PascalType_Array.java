package ast.core;

import writer.TypeManager;

public class PascalType_Array extends PascalType {
    private PascalType m_BaseType;
    private int m_Size;

    public PascalType_Array(PascalType baseTpye, int size) {
        m_BaseType = baseTpye;
        m_Size = size;
    }

    @Override
    public String GetTypeName() {
        String baseTypeName = m_BaseType.GetTypeName();
        return TypeManager.MakeArrayType(baseTypeName, m_Size);
    }
}
