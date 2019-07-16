package ast.core.functions.memory;

import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.memory.NativeFunction_free;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

public class FuncDeclNode_dispose extends FuncDeclNode_Core {
    public FuncDeclNode_dispose() {
        super(Memory.DISPOSE, VoidTypeNode.VoidNode);
        m_bCustomCallLogic = true;
        m_bInline = true;
    }

    @Override
    public FuncDeclNode ValidateCall(FuncCallNode callNode) {
        if (callNode.GetParameterCount() != 1) {
            return null;
        }

        TypeNode ParamType = callNode.GetParameter(0).GetType();
        if (!(ParamType.GetCompareType() instanceof PointerTypeNode)) {
            return null;
        }

        // TODO: use voidptr to validate any type of pointer?
        return this;
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        // Load variable that should get freed
        ParamContainer targetParam = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer loadedParam = AccessInterface.TryLoadValue(slave, callNode.GetParameter(0), targetParam);

        // free the referenced heap memory
        ParamContainer castPtr = slave.BitCast(loadedParam, TypeWrapper_Pointer.INTPTR);
        slave.CreateNativeCall(new NativeFunction_free(castPtr));

        return ParamContainer.VOIDCONTAINER();
    }
}
