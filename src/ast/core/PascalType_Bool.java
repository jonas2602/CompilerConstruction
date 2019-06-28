package ast.core;

import ast.EPrimitiveType;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;

public class PascalType_Bool extends PascalType_Primitive {
    public PascalType_Bool() {
        super(EPrimitiveType.BOOL, 1, false, "%d", "0");
    }

    public static abstract class FuncDeclNode_BoolParam extends PascalType_Operation {
        public FuncDeclNode_BoolParam(String InName, FunctionCall operation) {
            super(InName, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.BoolNode, operation);
        }
    }

    public static class FuncDeclNode_orBool extends FuncDeclNode_BoolParam {
        public FuncDeclNode_orBool() {
            super("operator||", GeneratorSlave::OrBoolBool);
        }
    }

    public static class FuncDeclNode_andBool extends FuncDeclNode_BoolParam {
        public FuncDeclNode_andBool() {
            super("operator&&", GeneratorSlave::AndBoolBool);
        }
    }
}
