package ast.core;

import ast.EPrimitiveType;
import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;

public class PascalType_Double extends PascalType_Primitive {
    public PascalType_Double() {
        super(EPrimitiveType.DOUBLE, 64, true, "%f", "0.0");
    }

}
