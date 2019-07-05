package ast.core;

import ast.EPrimitiveType;

public class PascalType_Int extends PascalType_Primitive {
    public PascalType_Int() {
        super(EPrimitiveType.INT, 32, false, "%d", "0");
    }
}
