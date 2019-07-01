package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public abstract class PascalType_Assignment extends PascalType_Operator {
    public PascalType_Assignment(Operator operator, TypeNode InReturnType, TypeNode lparam, TypeNode rparam, FunctionCallTwoParams operation) {
        super(operator, InReturnType, lparam, rparam, operation);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
        AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);
        ParamContainer leftParam = lParam.CreateSnippet(slave);
        ParamContainer rightParam = rParam.CreateSnippet(slave);

        return operation.createFunctionCall(slave, leftParam, rightParam);
    }
}
