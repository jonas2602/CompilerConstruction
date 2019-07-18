package ast.core.operators;

import ast.BlockNode;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.functions.array.ArrayFunction;
import ast.declarations.FuncDeclNode;
import ast.expressions.AccessNode_Field;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.types.*;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Array;

import javax.lang.model.type.ArrayType;

public class ArrayOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AGNArray());
    }

    public static class AGNArray extends PascalType_Assignment {
        public AGNArray() {
            super(Operator.AGN, new VoidTypeNode(), ArrayTypeNode.WildcardArrayNode(), (slave, lParam, rParam) -> {
                slave.CopyMemory(rParam, lParam);
                return null;
            });
        }

        @Override
        public FuncDeclNode ValidateCall(FuncCallNode callNode) {
            if (super.ValidateCall(callNode) == null) {
                return null;
            }

            int varSize = ((ArrayTypeNode) callNode.GetParameterType(0)).GetSize();
            int expSize = ((ArrayTypeNode) callNode.GetParameterType(1)).GetSize();
            if (varSize < expSize) {
                throw new TypeCheckException(this, "Arrays can only be copied if the target array is the same size or larger than the source array");
            }

            return this;
        }
    }
}