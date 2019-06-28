package ast.core;

import ast.EPrimitiveType;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;

public class PascalType_Bool extends PascalType_Primitive {
    public PascalType_Bool() {
        super(EPrimitiveType.BOOL, 1, false, "%d", "0");
    }
}
