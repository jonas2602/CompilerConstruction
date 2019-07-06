package ast.core.functions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_printf;
import writer.natives.NativeFunction_scanf;
import writer.wrapper.ParamContainer;
import writer.wrapper.ValueWrapper_Constant;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode_readln extends FuncDeclNode_Core {
    private ParamContainer m_FormatParam = null;

    // TODO: maybe use puts instead of printf (no format required)
    public FuncDeclNode_readln() {
        super("readln", PrimitiveTypeNode.IntNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("left", ArrayTypeNode.CharArrayNode));
    }

//    @Override
//    public boolean ValidateCall(FuncCallNode callNode) {
//        // Allows any amount of parameters, as long as they are primitive/can get serialized
//        // Compare given parameters to primitive types
//        for (AbstractSyntaxTree param : callNode.GetParameterList()) {
//            TypeNode CallParamType = param.GetType();
//            // if (!NamedTypeNode.IsPrimitiveType(CallParamType, false)) {
//            if (!(CallParamType instanceof PrimitiveTypeNode || PointerTypeNode.CharPointerNode.CompareType(CallParamType) || ArrayTypeNode.CharArrayNode.CompareType(CallParamType))) {
//                throw new TypeCheckException(this, "writeln only supports primitive types or charptr/chararray(strings)");
//            }
//
//            // TODO: check for tostring method
//            //  signature: ([type]) -> char*
//        }
//
//        return true;
//    }

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
