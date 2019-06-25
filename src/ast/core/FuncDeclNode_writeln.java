package ast.core;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;
import llvm.*;
import writer.GeneratorSlave;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode_writeln extends FuncDeclNode_Core {
    public FuncDeclNode_writeln() {
        super("writeln", NamedTypeNode.VoidNode);
        m_bCustomCallLogic = true;
    }

    // Allows any amount of parameters, as long as they are primitive/can get serialized

    @Override
    public boolean ValidateCall(FuncCallNode InCallNode) {
        // Compare given parameters to primitive types
        for (AbstractSyntaxTree param : InCallNode.GetParameterList()) {
            TypeNode CallParamType = param.GetType();
            if (!NamedTypeNode.IsPrimitiveType(CallParamType, false)) {
                // throw new TypeCheckException(this, "writeln only supports primitive types");
                return false;
            }
        }

        return true;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        // Only build once
        if (m_IsCreated) return null;
        m_IsCreated = true;

        CodeSnippet_FuncDecl decl = slave.CreateFunctionDeclaration("printf", CodeSnippet_Type.SNIPPETTYPE_INT);
        decl.AddParameter(new CodeSnippet_Plain("i8*"));
        decl.AddParameter(new CodeSnippet_Plain("..."));

        return decl;
    }

    @Override
    public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
        // TODO: only one element with a single character? -> use "putchar"
        // TODO: add constants directly to the placeholder string, instead of adding a parameter

        String placeholder = "";
        List<CodeSnippet_Base> filler = new ArrayList<>();

        for (AbstractSyntaxTree element : callNode.GetParameterList()) {
            // if (element instanceof ConstantNode) {
            //     placeholder += ((ConstantNode) element).GetData();
            // }

            // Create Parameter for printf call
            CodeSnippet_Parameter param = callNode.CreateParameterSnippet(slave, ctx, element);
            filler.add(param);

            // Add placeholder element for parameter to string
            CodeSnippet_Type.EType llvmType = param.GetTypeSnippet().GetType();
            placeholder += llvmType.placeholder();
        }

        //
        CodeSnippet_Constant constant = slave.CreateStringConstant(placeholder);
        CodeSnippet_Base placeholderParam = slave.CreateStringParameter(constant);
        CodeSnippet_FuncCall call = slave.CreatePrintfCall(placeholderParam, filler);

        return call;
    }
}
