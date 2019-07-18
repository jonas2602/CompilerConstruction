package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.core.FunctionNameWrapper;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public abstract class PascalType_SingleOperator extends FuncDeclNode_Core {

    private FunctionCallOneParam m_Operation;

    public PascalType_SingleOperator(String name, TypeNode returnType, TypeNode param, FunctionCallOneParam operation) {
        super(name, returnType);

        AddParameter(new ParamDeclNode("left", param));

        m_bCustomCallLogic = true;
        m_bInline = true;

        m_Operation = operation;
    }

    public PascalType_SingleOperator(FunctionNameWrapper functionName, TypeNode returnType, TypeNode param, FunctionCallOneParam operation) {
        this(functionName.GetFunctionName(), returnType, param, operation);
    }

    public PascalType_SingleOperator(FunctionNameWrapper functionName, TypeNode type, FunctionCallOneParam operation) {
        this(functionName.GetFunctionName(), type, type, operation);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree param = callNode.GetParameterList().get(0);
        ParamContainer paramContainer = param.CreateSnippet(slave);

        // load value if requested from a variable
        paramContainer = AccessInterface.TryLoadValue(slave, param, paramContainer);

        return m_Operation.createFunctionCall(slave, paramContainer);
    }
}