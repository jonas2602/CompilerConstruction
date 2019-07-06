package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

public abstract class PascalType_Assignment extends PascalType_Operator {
    public PascalType_Assignment(Operator operator, TypeNode returnType, TypeNode lparam, TypeNode rparam, FunctionCallTwoParams operation) {
        super(operator, returnType, lparam, rparam, operation);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
        AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);
        ParamContainer leftParam = lParam.CreateSnippet(slave);
        ParamContainer rightParam = rParam.CreateSnippet(slave);

        return m_Operation.createFunctionCall(slave, leftParam, rightParam);
    }
}
