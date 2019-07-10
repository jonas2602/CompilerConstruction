package ast.core.functions.memory;

import ast.core.FuncDeclNode_Core;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_realloc;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

public class FuncDeclNode_reallocmem extends FuncDeclNode_Core {
    public FuncDeclNode_reallocmem() {
        super("reallocmem", PointerTypeNode.WildCardPointerNode());
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter("source", m_ReturnType);
        AddParameter("length", PrimitiveTypeNode.IntNode);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        // get func call parameter
        ParamContainer memPtrParam = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer lengthParam = callNode.GetParameter(1).CreateSnippet(slave);

        // convert params to fit native function call
        memPtrParam = slave.LoadFromVariable(memPtrParam);
        ParamContainer memCharPtr = slave.BitCast(memPtrParam, TypeWrapper_Pointer.CHARPTR);
        lengthParam = AccessInterface.TryLoadValue(slave, callNode.GetParameter(1), lengthParam);
        lengthParam = slave.ExtendToLong(lengthParam);

        // native call to reallocate memory
        ParamContainer outCharPtr = slave.CreateNativeCall(new NativeFunction_realloc(memCharPtr, lengthParam));

        // convert native result back to requested type
        return slave.BitCast(outCharPtr, memPtrParam.GetRootType());
    }
}