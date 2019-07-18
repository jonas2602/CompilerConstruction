package ast.core.functions.array.fixed;

import ast.BlockNode;
import ast.core.StdBuilder;
import ast.core.functions.array.ArrayFunction;
import ast.core.operators.PascalType_SingleOperator;
import ast.types.ArrayTypeNode;
import ast.types.PrimitiveTypeNode;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Array;

public class FixedArrayFunctions implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new HighArray());
        std.AddFunctionDeclaration(new LowArray());
        // std.AddFunctionDeclaration(new LengthArray());
    }

    public static class HighArray extends PascalType_SingleOperator {
        public HighArray() {
            super(ArrayFunction.HIGH, PrimitiveTypeNode.IntNode, ArrayTypeNode.WildcardArrayNode(), (slave, lParam) -> {
                TypeWrapper_Array arr = (TypeWrapper_Array)lParam.GetRootType();
                return ParamContainer.INTCONTAINER(arr.GetRawSize() - 1);
            });
        }
    }

    public static class LowArray extends PascalType_SingleOperator {
        public LowArray() {
            super(ArrayFunction.LOW, PrimitiveTypeNode.IntNode, ArrayTypeNode.WildcardArrayNode(), (slave, lParam) -> ParamContainer.INTCONTAINER(0));
        }
    }

    public static class LengthArray extends PascalType_SingleOperator {
        public LengthArray() {
            super(ArrayFunction.LENGTH, PrimitiveTypeNode.IntNode, ArrayTypeNode.WildcardArrayNode(), (slave, lParam) -> {
                TypeWrapper_Array arr = (TypeWrapper_Array)lParam.GetRootType();
                return ParamContainer.INTCONTAINER(arr.GetRawSize());
            });
        }
    }
}
