package ast.core;

import ast.EPrimitiveType;

public class PascalType_Long extends PascalType_Primitive {
    public PascalType_Long() {
        super(EPrimitiveType.LONG, 64, false, "%l", "0");
    }
}
