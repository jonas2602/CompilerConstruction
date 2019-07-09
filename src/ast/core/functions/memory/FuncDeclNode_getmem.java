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
        super("getmem", VoidTypeNode.VoidNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter("ptr", PointerTypeNode.WildCardPointerNode());
        AddParameter("size", PrimitiveTypeNode.IntNode);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        // get func call parameter
        ParamContainer memPtrParam = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer bitsizeParam = callNode.GetParameter(1).CreateSnippet(slave);

        // Allocate Memory
        ParamContainer bitsizeValue = AccessInterface.TryLoadValue(slave, callNode.GetParameter(1), bitsizeParam);
        bitsizeValue = slave.ExtendToLong(bitsizeValue);
        ParamContainer outCharPtr = slave.CreateNativeCall(new NativeFunction_malloc(bitsizeValue));

        // store allocated memory in given ptr
        ParamContainer memPtrValue = slave.LoadFromVariable(memPtrParam);
        ParamContainer newMemory = slave.BitCast(outCharPtr, memPtrValue.GetRootType());
        slave.StoreInVariable(memPtrParam, newMemory);

        return ParamContainer.VOIDCONTAINER();
    }
}
