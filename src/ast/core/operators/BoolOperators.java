package ast.core.operators;

import ast.BlockNode;
import ast.core.StdBuilder;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;

public class BoolOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        //prefix operators
        std.AddFunctionDeclaration(new NegateBool());

        std.AddFunctionDeclaration(new OrBool());
        std.AddFunctionDeclaration(new AndBool());
    }

    public static class NegateBool extends PascalType_SingleOperator {
        public NegateBool() {
            super(Operator.NEG, PrimitiveTypeNode.BoolNode, GeneratorSlave::NegateBool);
        }
    }

    public static abstract class BoolOperator extends PascalType_Operator {
        public BoolOperator(Operator operator, FunctionCallTwoParams operation) {
            super(operator, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.BoolNode, operation);
        }
    }

    public static class OrBool extends BoolOperator {
        public OrBool() {
            super(Operator.OR, GeneratorSlave::OrBoolBool);
        }
    }

    public static class AndBool extends BoolOperator {
        public AndBool() {
            super(Operator.AND, GeneratorSlave::AndBoolBool);
        }
    }
}
