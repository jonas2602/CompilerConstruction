package ast.core;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.expressions.ParameterNode;
import ast.types.NamedTypeNode;
import ast.types.PrimitiveTypeNode;
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
            // if (!NamedTypeNode.IsPrimitiveType(CallParamType, false)) {
            if (!(CallParamType instanceof PrimitiveTypeNode)) {
                // throw new TypeCheckException(this, "writeln only supports primitive types");
                System.out.println("writeln only supports primitive types");
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

        String placeholderString = "";
        List<CodeSnippet_Base> filler = new ArrayList<>();

        for (AbstractSyntaxTree element : callNode.GetParameterList()) {
            // if (element instanceof ConstantNode) {
            //     placeholder += ((ConstantNode) element).GetData();
            // }

            TypeNode typeNode = element.GetType();
            CodeSnippet_Base typeSnippet = typeNode.CreateSnippet(slave, ctx);
            CodeSnippet_Base dataSnippet = element.CreateSnippet(slave, ctx);

            // Create Parameter for printf call
            PrimitiveTypeNode primType = (PrimitiveTypeNode) element.GetType();
            if (primType.GetTypeIsDezimal()) {
                // convert all decimals to double
                if (primType.GetTypeSize() != 64) {

                    typeSnippet = new CodeSnippet_Plain("double");
                    dataSnippet = new CodeSnippet_Plain("%" + slave.ExtendFloatToDouble(dataSnippet.Write()));
                }
            }

            filler.add(new CodeSnippet_Parameter(typeSnippet, dataSnippet));

            // Add placeholder element for parameter to string
            placeholderString += primType.GetTypePlaceholder();
        }
        // TODO: Add newline (must be added as hex (\0A instead of \n, counts as a single character))
        // placeholderString += "\0A";

        //
        CodeSnippet_Constant constant = slave.CreateStringConstant(placeholderString);
        CodeSnippet_Base placeholderParam = slave.CreateStringParameter(constant);
        CodeSnippet_FuncCall call = slave.CreatePrintfCall(placeholderParam, filler);

        return call;
    }
}
