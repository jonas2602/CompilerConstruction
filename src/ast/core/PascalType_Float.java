package ast.core;

import ast.EPrimitiveType;

public class PascalType_Float extends PascalType_Primitive {
    public PascalType_Float() {
        super(EPrimitiveType.FLOAT, 32, true, "%f", "0.0");
    }
}
