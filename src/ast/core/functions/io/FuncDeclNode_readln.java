package ast.core.functions.io;

import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.natives.io.NativeFunction_scanf;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_readln extends FuncDeclNode_Core {
    private ParamContainer m_FormatParam = null;

    // TODO: maybe use puts instead of printf (no format required)
    public FuncDeclNode_readln() {
        super(IO.READLN, PrimitiveTypeNode.IntNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("left", ArrayTypeNode.CharArrayNode));
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        // load format string
        if (m_FormatParam == null) {
            m_FormatParam = slave.CreateStringConstant(" %[^\n]");
        }
        ParamContainer formatString = slave.CreateArrayElementPtr(m_FormatParam, 0);

        // Get Out String reference
        ParamContainer outString = callNode.GetParameter(0).CreateSnippet(slave);
        ParamContainer outStringPtr = slave.CreateArrayElementPtr(outString, 0);

        // Create Native Call
        NativeFunction_scanf scan = new NativeFunction_scanf(formatString, outStringPtr);
        return slave.CreateNativeCall(scan);
    }
}
