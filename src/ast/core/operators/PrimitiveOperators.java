package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.core.StdBuilder;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class PrimitiveOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AGNPrimitive());
    }

    public static class AGNPrimitive extends PascalType_Operator {
        public AGNPrimitive() {
            super(Operator.AGN, PrimitiveTypeNode.BoolNode, PrimitiveTypeNode.WildcardPrimitiveNode(), null);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            AbstractSyntaxTree lParam = callNode.GetParameter(0);
            AbstractSyntaxTree rParam = callNode.GetParameter(1);
            ParamContainer leftParam = lParam.CreateSnippet(slave);
            ParamContainer rightParam = rParam.CreateSnippet(slave);

            rightParam = AccessInterface.TryLoadValue(slave, rParam, rightParam);
            slave.StoreInVariable(leftParam, rightParam);
            return null;
        }
    }
}