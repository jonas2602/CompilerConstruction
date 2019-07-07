package ast.core.operators;

import ast.BlockNode;
import ast.TypeCheckException;
import ast.core.StdBuilder;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode;
import ast.types.VoidTypeNode;

public class ArrayOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AGNArray());
    }

    // public static abstract class ArrayOperator extends PascalType_Assignment {
    //     public ArrayOperator(Operator operator, TypeNode returnType, FunctionCallTwoParams operation) {
    //         super(operator, returnType, ArrayTypeNode.WildCardArrayNode, ArrayTypeNode.WildCardArrayNode, operation);
    //     }
    // }

    public static class AGNArray extends PascalType_Assignment {
        public AGNArray() {
            super(Operator.AGN, new VoidTypeNode(), ArrayTypeNode.WildCardArrayNode, ArrayTypeNode.WildCardArrayNode, (slave, lParam, rParam) -> {
                slave.CopyMemory(rParam, lParam);
                return null;
            });
        }

        @Override
        public boolean ValidateCall(FuncCallNode callNode) {
            if (!super.ValidateCall(callNode)) {
                return false;
            }

            int varSize = ((ArrayTypeNode) callNode.GetParameterList().get(0).GetType()).GetSize();
            int expSize = ((ArrayTypeNode) callNode.GetParameterList().get(1).GetType()).GetSize();
            if (varSize < expSize) {
                throw new TypeCheckException(this, "Arrays can only be copied if the target array is the same size or larger than the source array");
            }

            return true;
        }
    }
}