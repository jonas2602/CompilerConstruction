package ast.core.functions.memory;

import ast.core.FuncDeclNode_Core;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_malloc;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_new extends FuncDeclNode_Core {
    public FuncDeclNode_new() {
        super("new", VoidTypeNode.VoidNode);
        m_bCustomCallLogic = true;
        m_bInline = true;
    }

    @Override
    public boolean ValidateCall(FuncCallNode callNode) {
        if (callNode.GetParameterCount() != 1) {
            return false;
        }

        TypeNode paramType = callNode.GetParameter(0).GetType();
        if (!(paramType.GetCompareType() instanceof PointerTypeNode)) {
            return false;
        }

        // TODO: use voidptr to validate any type of pointer?
        return true;
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer targetParam = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer heapPtr = slave.CreateNativeCall(new NativeFunction_malloc(targetParam.GetRootType(), 1));
        ParamContainer castPtr = slave.BitCast(heapPtr, targetParam.GetRootType().GetChild());
        slave.StoreInVariable(targetParam, castPtr);

        return ParamContainer.VOIDCONTAINER();
    }
}
