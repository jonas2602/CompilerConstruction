package ast.core.functions.memory;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_malloc;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_getmem extends FuncDeclNode_Core {
    public FuncDeclNode_getmem() {
        super("getmem", PointerTypeNode.VoidPointerNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("size", PrimitiveTypeNode.IntNode));
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree param = callNode.GetParameter(0);
        ParamContainer container = param.CreateSnippet(slave);
        container = AccessInterface.TryLoadValue(slave, param, container);

        return slave.CreateNativeCall(new NativeFunction_malloc(container));
    }
}
