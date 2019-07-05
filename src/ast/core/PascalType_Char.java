package ast.core;

import ast.EPrimitiveType;

public class PascalType_Char extends PascalType_Primitive {
    public PascalType_Char() {
        super(EPrimitiveType.CHAR, 8, false, "%c", "0");
    }
}

