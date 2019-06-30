package ast.core;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.NamedTypeNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import llvm.*;
import writer.ConstantWrapper;
import writer.GeneratorSlave;
import writer.ParamContainer;

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
            if (!(CallParamType instanceof PrimitiveTypeNode || new PointerTypeNode(PrimitiveTypeNode.CharNode).CompareType(CallParamType))) {
                throw new TypeCheckException(this, "writeln only supports primitive types or charptr(strings)");
            }

            // TODO: check for tostring method
            //  signature: ([type]) -> char*
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
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        // TODO: only one element with a single character? -> use "putchar"
        // TODO: add constants directly to the placeholder string, instead of adding a parameter

        String placeholderString = "";
        List<CodeSnippet_Base> filler = new ArrayList<>();

        for (AbstractSyntaxTree element : callNode.GetParameterList()) {
            // if (element instanceof ConstantNode) {
            //     placeholder += ((ConstantNode) element).GetData();
            // }

            ParamContainer elementContainer = element.CreateSnippet(slave);

            // load value if requested from a variable
            elementContainer = AccessInterface.TryLoadValue(slave, element, elementContainer);
            // if (element instanceof AccessInterface) {
            //     elementContainer = slave.LoadFromVariable(elementContainer);
            // }

            // Create Parameter for printf call
            PrimitiveTypeNode primType = (PrimitiveTypeNode) element.GetType();
            if (primType.GetTypeIsDezimal()) {
                // convert all decimals to double
                if (primType.GetTypeSize() != 64) {
                    elementContainer = slave.ExtendFloatToDouble(elementContainer);
                }
            }

            filler.add(new CodeSnippet_Plain(elementContainer.CreateParameterString()));

            // Add placeholder element for parameter to string
            placeholderString += primType.GetTypePlaceholder();
        }
        // TODO: Add newline (must be added as hex (\0A instead of \n, counts as a single character))
        placeholderString += "\n";

        //
        ParamContainer constant = slave.CreateStringConstantNew(placeholderString);
        ParamContainer placeholderRef = slave.CreateArrayElementPtr(constant, new ConstantWrapper("0"));
        CodeSnippet_Base placeholderParam = new CodeSnippet_Plain(placeholderRef.CreateParameterString());
        slave.CreatePrintfCall(placeholderParam, filler);

        return null;
    }
}
