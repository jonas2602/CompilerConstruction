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
        std.AddFunctionDeclaration(new BoolChar());
    }

    public static class ChrInt extends PascalType_SingleOperator {
        public ChrInt() {
            super(Cast.CHR, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.IntNode, GeneratorSlave::TruncToChar);
        }
    }

    public static class OrdInt extends PascalType_SingleOperator {
        public OrdInt() {
            super(Cast.ORD, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, lParam) -> lParam);
        }
    }

    public static class OrdChar extends PascalType_SingleOperator {
        public OrdChar() {
            super(Cast.ORD, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.CharNode, GeneratorSlave::ExtendToInt);
        }
    }

    public static class OrdBool extends PascalType_SingleOperator {
        public OrdBool() {
            super(Cast.ORD, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.BoolNode, GeneratorSlave::ExtendToInt);
        }
    }

    public static class BoolChar extends PascalType_SingleOperator {
        public BoolChar() {
            super(Cast.BOOL, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.CharNode, GeneratorSlave::TruncToBool);
        }
    }
}
