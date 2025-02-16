package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.core.StdBuilder;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class PointerOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new EQPointer());
        std.AddFunctionDeclaration(new NEPointer());
        std.AddFunctionDeclaration(new AGNPointer());
    }

    public static abstract class PointerOperator extends PascalType_Operator {
        public PointerOperator(Operator operator, TypeNode returnType, FunctionCallTwoParams operation) {
            super(operator, returnType, PointerTypeNode.WildCardPointerNode(), operation);
        }
    }

    public static class EQPointer extends PointerOperator {
        public EQPointer() {
            super(Operator.EQ, PrimitiveTypeNode.BoolNode, GeneratorSlave::EQIType);
        }
    }

    public static class NEPointer extends PointerOperator {
        public NEPointer() {
            super(Operator.NE, PrimitiveTypeNode.BoolNode, GeneratorSlave::NEIType);
        }
    }

    public static class AGNPointer extends PointerOperator {
        public AGNPointer() {
            super(Operator.AGN, VoidTypeNode.VoidNode, null);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            AbstractSyntaxTree lParam = callNode.GetParameter(0);
            AbstractSyntaxTree rParam = callNode.GetParameter(1);
            ParamContainer leftParam = lParam.CreateSnippet(slave);
            ParamContainer rightParam = rParam.CreateSnippet(slave);

            rightParam = AccessInterface.TryLoadValue(slave, rParam, rightParam);
            slave.StoreInVariable(leftParam, rightParam);
            return ParamContainer.VOIDCONTAINER();
        }
    }
}