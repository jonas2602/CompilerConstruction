package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

public abstract class PascalType_Prefix extends FuncDeclNode_Core {

    private FunctionCallOneParam operation;

    public PascalType_Prefix(Operator operator, TypeNode type, FunctionCallOneParam operation) {
        super(operator.GetOperatorFunctionName(), type);

        AddParameter(new ParamDeclNode("left", type));

        m_bCustomCallLogic = true;
        m_bInline = true;

        this.operation = operation;
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree param = callNode.GetParameterList().get(0);
        ParamContainer paramContainer = param.CreateSnippet(slave);

        // load value if requested from a variable
        paramContainer = AccessInterface.TryLoadValue(slave, param, paramContainer);
        // if (param instanceof AccessInterface) {
        //     paramContainer = slave.LoadFromVariable(paramContainer);
        // }

        return operation.createFunctionCall(slave, paramContainer);
    }
}