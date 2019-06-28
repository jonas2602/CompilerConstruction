package ast.core;

import ast.EPrimitiveType;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.TypeContainer;

public class PascalType_Bool extends PascalType_Primitive {
    public PascalType_Bool() {
        super(EPrimitiveType.BOOL, 1, false, "%d", "0");
    }

    public static abstract class FuncDeclNode_BoolParam extends PascalType_Operation {
        public FuncDeclNode_BoolParam(String InName, TypeNode InReturnType, PrimitiveTypeNode rparam, FunctionCall operation) {
            super(InName, InReturnType, PrimitiveTypeNode.BoolNode, rparam, operation);
        }
    }

    public static class FuncDeclNode_orBool extends PascalType_Int.FuncDeclNode_IntParam {
        public FuncDeclNode_orBool() {
            super("operator||", PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.BoolNode, GeneratorSlave::OrBoolBool);
        }
    }
}
