package ast.core;

import ast.EPrimitiveType;

public class PascalType_Bool extends PascalType_Primitive {
    public PascalType_Bool() {
        super(EPrimitiveType.BOOL, 1, false, "%d", "0");
    }
}
