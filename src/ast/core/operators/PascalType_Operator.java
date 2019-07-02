package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public abstract class PascalType_Operator extends FuncDeclNode_Core {

    protected FunctionCallTwoParams operation;

    public PascalType_Operator(Operator operator, TypeNode InReturnType, TypeNode lparam, TypeNode rparam, FunctionCallTwoParams operation) {
        super(operator.GetOperatorFunctionName(), InReturnType);

        AddParameter(new ParamDeclNode("left", lparam));
        AddParameter(new ParamDeclNode("right", rparam));

        m_bCustomCallLogic = true;
        m_bInline = true;

        this.operation = operation;
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
        AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);
        ParamContainer leftParam = lParam.CreateSnippet(slave);
        ParamContainer rightParam = rParam.CreateSnippet(slave);

        // load values if requested from a variable
        leftParam = AccessInterface.TryLoadValue(slave, lParam, leftParam);
        rightParam = AccessInterface.TryLoadValue(slave, rParam, rightParam);

        return operation.createFunctionCall(slave, leftParam, rightParam);
    }
}