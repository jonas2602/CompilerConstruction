package ast.core.functions.memory;

import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_malloc;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

public class FuncDeclNode_new extends FuncDeclNode_Core {
    public FuncDeclNode_new() {
        super("new", VoidTypeNode.VoidNode);
        m_bCustomCallLogic = true;
        m_bInline = true;
    }

    @Override
    public FuncDeclNode ValidateCall(FuncCallNode callNode) {
        if (callNode.GetParameterCount() != 1) {
            return null;
        }

        TypeNode paramType = callNode.GetParameter(0).GetType();
        if (!(paramType.GetCompareType() instanceof PointerTypeNode)) {
            return null;
        }

        // TODO: use voidptr to validate any type of pointer?
        return this;
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer targetParam = callNode.GetParameter(0).CreateSnippet(slave);
        TypeWrapper elementType = targetParam.GetRootType().GetChild().GetChild();
        ParamContainer heapPtr = slave.CreateNativeCall(new NativeFunction_malloc(elementType, 1));
        ParamContainer castPtr = slave.BitCast(heapPtr, targetParam.GetRootType().GetChild());
        slave.StoreInVariable(targetParam, castPtr);

        return ParamContainer.VOIDCONTAINER();
    }
}
