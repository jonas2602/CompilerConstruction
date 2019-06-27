package writer;

import ast.core.PascalType_Char;

public abstract class TypeWrapper {
    public abstract String GetTypeName();
    public abstract TypeWrapper GetChild();

}
