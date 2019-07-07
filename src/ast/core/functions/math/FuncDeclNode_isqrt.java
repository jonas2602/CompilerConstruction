package ast.core.functions.math;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.natives.math.NativeFunction_sqrt;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_isqrt extends FuncDeclNode_Core {
    public FuncDeclNode_isqrt() {
        super("sqrt", PrimitiveTypeNode.FloatNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("param", PrimitiveTypeNode.IntNode));
    }

    @Override
    public boolean ValidateCall(FuncCallNode callNode) {
        if (callNode.GetParameterCount() != 1) {
            return false;
        }

        return PrimitiveTypeNode.IntNode.CompareType(callNode.GetParameter(0).GetType());
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree param = callNode.GetParameter(0);
        ParamContainer container = param.CreateSnippet(slave);

        container = AccessInterface.TryLoadValue(slave, param, container);

        ParamContainer cast = slave.CastIntToFloat(container);
        ParamContainer result = slave.CreateNativeCall(new NativeFunction_sqrt(cast));

        return result;
    }
}
