package ast.core.functions.memory;

import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.memory.NativeFunction_memcpy;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_move extends FuncDeclNode_Core {
    public FuncDeclNode_move() {
        super(Memory.MOVE, VoidTypeNode.VoidNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter("dest", PointerTypeNode.CharPointerNode);
        AddParameter("source", PointerTypeNode.CharPointerNode);
        AddParameter("size", PrimitiveTypeNode.IntNode);
    }

    @Override
    public FuncDeclNode ValidateCall(FuncCallNode callNode) {
        return super.ValidateCall(callNode);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer destParam = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer sourceParam = callNode.GetParameter(1).CreateSnippet(slave);
        ParamContainer sizeParam = callNode.GetParameter(2).CreateSnippet(slave);
        
        sizeParam = slave.ExtendToLong(sizeParam);
        slave.CreateNativeCall(new NativeFunction_memcpy(destParam, sourceParam, sizeParam));

        return ParamContainer.VOIDCONTAINER();
    }
}
