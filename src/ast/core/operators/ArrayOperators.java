package ast.core.operators;

import ast.BlockNode;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
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
        std.AddFunctionDeclaration(new HighArray());
        std.AddFunctionDeclaration(new LowArray());
        std.AddFunctionDeclaration(new HighArrayDynamic());
        std.AddFunctionDeclaration(new LowArrayDynamic());
    }

    // public static abstract class ArrayOperator extends PascalType_Assignment {
    //     public ArrayOperator(Operator operator, TypeNode returnType, FunctionCallTwoParams operation) {
    //         super(operator, returnType, ArrayTypeNode.WildCardArrayNode, ArrayTypeNode.WildCardArrayNode, operation);
    //     }
    // }

    public static class HighArray extends PascalType_SingleOperator {
        public HighArray() {
            super(Operator.HIGH, PrimitiveTypeNode.IntNode, ArrayTypeNode.WildcardArrayNode(), (slave, lParam) -> {
                TypeWrapper_Array arr = (TypeWrapper_Array)lParam.GetRootType();
                return ParamContainer.INTCONTAINER(arr.GetRawSize() - 1);
            });
        }
    }

    public static class LowArray extends PascalType_SingleOperator {
        public LowArray() {
            super(Operator.LOW, PrimitiveTypeNode.IntNode, ArrayTypeNode.WildcardArrayNode(), (slave, lParam) -> ParamContainer.INTCONTAINER(0));
        }
    }

    public static class HighArrayDynamic extends FuncDeclNode_Core {
        public HighArrayDynamic() {
            super(Operator.HIGH, PrimitiveTypeNode.IntNode);
            AddParameter("array", ArrayTypeNode_Dynamic.WildcardArrayNode());

            m_bInline = true;

            AccessNode_Variable arrAccess = new AccessNode_Variable("array");
            AccessNode_Field fieldAccess = new AccessNode_Field(arrAccess, "length");
            FuncCallNode subCall = new FuncCallNode(Operator.SUB, fieldAccess, ConstantNode.IntNode(1));

            m_Block.SetCompoundStatement(subCall);
        }
    }

    public static class LowArrayDynamic extends PascalType_SingleOperator {
        public LowArrayDynamic() {
            super(Operator.LOW, PrimitiveTypeNode.IntNode, ArrayTypeNode_Dynamic.WildcardArrayNode(), (slave, lParam) -> ParamContainer.INTCONTAINER(0));
        }
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