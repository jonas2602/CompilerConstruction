package ast.core.operators;

import ast.BlockNode;
import ast.TypeCheckException;
import ast.core.StdBuilder;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode;
import ast.types.RecordTypeNode;
import ast.types.VoidTypeNode;

public class RecordOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AGNStruct());
    }

    // public static abstract class ArrayOperator extends PascalType_Assignment {
    //     public ArrayOperator(Operator operator, TypeNode returnType, FunctionCallTwoParams operation) {
    //         super(operator, returnType, ArrayTypeNode.WildCardArrayNode, ArrayTypeNode.WildCardArrayNode, operation);
    //     }
    // }

    public static class AGNStruct extends PascalType_Assignment {
        public AGNStruct() {
            super(Operator.AGN, new VoidTypeNode(), RecordTypeNode.WildCardRecordNode, RecordTypeNode.WildCardRecordNode, (slave, lParam, rParam) -> {
                slave.CopyMemory(rParam, lParam);
                return null;
            });
        }
    }
}