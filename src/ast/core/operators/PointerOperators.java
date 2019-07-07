package ast.core.operators;

import ast.BlockNode;
import ast.core.StdBuilder;
import ast.expressions.AccessInterface;
import ast.types.*;
import writer.GeneratorSlave;

public class PointerOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new EQPointer());
        std.AddFunctionDeclaration(new NEPointer());
        // std.AddFunctionDeclaration(new AGNPointer());
    }

    public static abstract class PointerOperator extends PascalType_Operator {
        public PointerOperator(Operator operator, TypeNode returnType, FunctionCallTwoParams operation) {
            super(operator, returnType, PointerTypeNode.WildCardPointerNode, PointerTypeNode.WildCardPointerNode, operation);
        }
    }

    public static class EQPointer extends PointerOperator {
        public EQPointer() {
            super(Operator.EQ, PrimitiveTypeNode.BoolNode, GeneratorSlave::IntEQ);
        }
    }

    public static class NEPointer extends PointerOperator {
        public NEPointer() {
            super(Operator.NE, PrimitiveTypeNode.BoolNode, GeneratorSlave::IntNE);
        }
    }

    public static class AGNPointer extends PascalType_Assignment {
        public AGNPointer() {
            super(Operator.AGN, PrimitiveTypeNode.BoolNode, PointerTypeNode.WildCardPointerNode, PointerTypeNode.WildCardPointerNode, (slave, lParam, rParam) -> {
                // if the expression on the right of the assignment is not a constant (variable access stuff)
                // 'exp' will contain a pointer to the requested value that must be loaded before writing
                rParam = AccessInterface.TryLoadValue(slave, m_Expression, rParam);
                slave.StoreInVariable(lParam, rParam);
            });
        }
    }
}