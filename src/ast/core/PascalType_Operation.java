package ast.core;

import ast.AbstractSyntaxTree;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public abstract class PascalType_Operation extends FuncDeclNode_Core {

    private FunctionCall operation;

    public PascalType_Operation(String InName, TypeNode InReturnType, PrimitiveTypeNode lparam, PrimitiveTypeNode rparam, FunctionCall operation) {
        super(InName, InReturnType);

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

        // load value if requested from a variable
        if (lParam instanceof AccessInterface) {
            leftParam = slave.LoadFromVariable(leftParam);
        }

        // load value if requested from a variable
        if (rParam instanceof AccessInterface) {
            rightParam = slave.LoadFromVariable(rightParam);
        }

        return operation.createFunctionCall(slave, leftParam, rightParam);
    }
}

interface FunctionCall {
    public ParamContainer createFunctionCall(GeneratorSlave slave, ParamContainer lParam, ParamContainer rParam);
}
