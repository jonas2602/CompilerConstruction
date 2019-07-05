package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.core.PascalType;
import ast.core.StdBuilder;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import ast.types.WildCardPointer;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

public class PointerOperators implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new EQPointer());
    }

    public static abstract class PointerOperator extends PascalType_Operator {
        public PointerOperator(Operator operator, TypeNode returnType, FunctionCallTwoParams operation) {
            super(operator, returnType, PointerTypeNode.WildCardPointerNode, PointerTypeNode.WildCardPointerNode, operation);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
            AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);

            PointerTypeNode lPointer = (PointerTypeNode) lParam.GetType();
            PointerTypeNode rPointer = (PointerTypeNode) rParam.GetType();

            if(!lPointer.CompareType(rPointer)) {
                throw new TypeCheckException(this, "Pointers have different types!");
            }

            ParamContainer leftParam = lParam.CreateSnippet(slave);
            ParamContainer rightParam = rParam.CreateSnippet(slave);
            
            leftParam = AccessInterface.TryLoadValue(slave, lParam, leftParam);
            rightParam = AccessInterface.TryLoadValue(slave, rParam, rightParam);

            return operation.createFunctionCall(slave, leftParam, rightParam);
        }
    }

    public static class EQPointer extends PointerOperator {
        public EQPointer() {
            super(Operator.EQ, PrimitiveTypeNode.BoolNode, GeneratorSlave::IntEQ);
        }
    }
}