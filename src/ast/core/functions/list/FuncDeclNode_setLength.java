package ast.core.functions.list;

import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode_Dynamic;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_realloc;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

public class FuncDeclNode_setLength extends FuncDeclNode_Core {
    public FuncDeclNode_setLength() {
        super("setlength", VoidTypeNode.VoidNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("arr", ArrayTypeNode_Dynamic.WildcardArrayNode));
        AddParameter(new ParamDeclNode("length", PrimitiveTypeNode.IntNode));
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        // get func call parameter
        ParamContainer dynArrParam = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer lengthParam = callNode.GetParameter(1).CreateSnippet(slave);

        // Get array start ptr as charptr
        ParamContainer startProp = slave.CreateArrayElementPtr(dynArrParam, ArrayTypeNode_Dynamic.DynamicStartIndex);
        ParamContainer startPropValue = slave.LoadFromVariable(startProp);
        ParamContainer startCharPtr = slave.BitCast(startPropValue, TypeWrapper_Pointer.CHARPTR);

        // Get value of new length parameter
        int lengthValue = Integer.parseInt(lengthParam.CreateDataString());

        // native call to reallocate memory
        ParamContainer outCharPtr = slave.CreateNativeCall(new NativeFunction_realloc(startCharPtr, lengthValue));

        // store new startptr in dynamic array record
        ParamContainer newStartParam = slave.BitCast(outCharPtr, startPropValue.GetRootType());
        slave.StoreInVariable(startProp, newStartParam);

        // update length value
        ParamContainer lengthProp = slave.CreateArrayElementPtr(dynArrParam, ArrayTypeNode_Dynamic.DynamicLengthIndex);
        slave.StoreInVariable(lengthProp, lengthParam);

        // TODO: initialize newly created elements

        return ParamContainer.VOIDCONTAINER();
    }
}
