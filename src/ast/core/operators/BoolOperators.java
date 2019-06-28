package ast.core.operators;

import ast.BlockNode;
import ast.core.StdBuilder;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;

public class BoolOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new OrBool());
        std.AddFunctionDeclaration(new AndBool());
    }

    public static abstract class BoolOperator extends PascalType_Operator {
        public BoolOperator(Operator operator, FunctionCall operation) {
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
