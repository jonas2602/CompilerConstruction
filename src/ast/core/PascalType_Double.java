package ast.core;

import ast.EPrimitiveType;

public class PascalType_Double extends PascalType_Primitive {
    public PascalType_Double() {
        super(EPrimitiveType.DOUBLE, 64, true, "%f", "0.0");
    }

}
