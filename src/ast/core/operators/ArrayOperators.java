package ast.core.operators;

import ast.BlockNode;
import ast.core.StdBuilder;
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
    }
}