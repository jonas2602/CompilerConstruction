package ast.core;

import ast.AbstractSyntaxTree;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_malloc;
import writer.wrapper.ParamContainer;
import writer.wrapper.TypeWrapper;

public class FuncDeclNode_new extends FuncDeclNode_Core {
    public FuncDeclNode_new() {
        super("new", new VoidTypeNode());
        m_bCustomCallLogic = true;
        m_bInline = true;
    }

    @Override
    public boolean ValidateCall(FuncCallNode callNode) {
        if (callNode.GetParameterCount() != 1) {
            return false;
        }

        TypeNode ParamType = callNode.GetParameter(0).GetType();
        if (!(ParamType.GetCompareType() instanceof PointerTypeNode)) {
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
