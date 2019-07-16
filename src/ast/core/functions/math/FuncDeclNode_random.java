package ast.core.functions.math;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.natives.math.NativeFunction_rand;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_random extends FuncDeclNode_Core {
    public FuncDeclNode_random() {
        super("random", PrimitiveTypeNode.IntNode);

        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter("limit", PrimitiveTypeNode.IntNode);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree limitParam = callNode.GetParameter(0);
        ParamContainer limitValue = limitParam.CreateSnippet(slave);
        limitValue = AccessInterface.TryLoadValue(slave, limitParam, limitValue);

        ParamContainer randInt = slave.CreateNativeCall(new NativeFunction_rand());
        return slave.ModIntInt(randInt, limitValue);
    }
}
