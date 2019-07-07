package ast.core.functions.math;

import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_malloc;
import writer.natives.math.NativeFunction_sqrt;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_fsqrt extends FuncDeclNode_Core {
    public FuncDeclNode_fsqrt() {
        super("sqrt", PrimitiveTypeNode.FloatNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("param", PrimitiveTypeNode.FloatNode));
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer param = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer result = slave.CreateNativeCall(new NativeFunction_sqrt(param));

        return result;
    }
}
