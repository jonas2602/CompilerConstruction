package ast.core.functions.list;

import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode_Dynamic;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_getLength extends FuncDeclNode_Core {
    public FuncDeclNode_getLength() {
        super("length", PrimitiveTypeNode.IntNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("arr", ArrayTypeNode_Dynamic.WildcardArrayNode()));
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer dynArrParam = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer lengthField = slave.CreateArrayElementPtr(dynArrParam, ArrayTypeNode_Dynamic.DynamicLengthIndex);
        ParamContainer lengthValue = slave.LoadFromVariable(lengthField);

        return lengthValue;
    }
}
