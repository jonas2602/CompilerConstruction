package ast.core.functions.casts;

import ast.BlockNode;
import ast.core.StdBuilder;
import ast.core.operators.PascalType_SingleOperator;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;

public class CastFunctions implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new ChrInt());

        std.AddFunctionDeclaration(new OrdInt());
        std.AddFunctionDeclaration(new OrdChar());
        std.AddFunctionDeclaration(new OrdBool());
    }

    public static class ChrInt extends PascalType_SingleOperator {
        public ChrInt() {
            super("chr", PrimitiveTypeNode.CharNode, PrimitiveTypeNode.IntNode, GeneratorSlave::TruncToChar);
        }
    }

    public static class OrdInt extends PascalType_SingleOperator {
        public OrdInt() {
            super("ord", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, lParam) -> lParam);
        }
    }

    public static class OrdChar extends PascalType_SingleOperator {
        public OrdChar() {
            super("ord", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.CharNode, GeneratorSlave::ExtendToInt);
        }
    }

    public static class OrdBool extends PascalType_SingleOperator {
        public OrdBool() {
            super("ord", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.BoolNode, GeneratorSlave::ExtendToInt);
        }
    }
}
