package ast.core;

import ast.EPrimitiveType;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class PascalType_Int extends PascalType_Primitive {
    public PascalType_Int() {
        super(EPrimitiveType.INT, 32, false, "%d", "0");
    }
}
