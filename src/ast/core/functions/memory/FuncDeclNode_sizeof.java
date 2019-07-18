package ast.core.functions.memory;

import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.VoidTypeNode;
import ast.types.WildcardTypeNode;
import writer.GeneratorSlave;
import writer.natives.memory.NativeFunction_memcpy;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_sizeof extends FuncDeclNode_Core {
    public FuncDeclNode_sizeof() {
        super(Memory.SIZE, PrimitiveTypeNode.IntNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter("var", new WildcardTypeNode());
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer varParam = callNode.GetParameter(0).CreateSnippet(slave);
        varParam = AccessInterface.TryLoadValue(slave, callNode.GetParameter(0), varParam);

        int typeSize = varParam.GetRootType().GetTypeByteSize(varParam.GetTypeAlignment());
        return ParamContainer.INTCONTAINER(typeSize);
    }
}
