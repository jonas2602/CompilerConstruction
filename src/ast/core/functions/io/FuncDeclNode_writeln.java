package ast.core.functions.io;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.natives.io.NativeFunction_printf;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Primitive;
import writer.wrappers.ValueWrapper_Constant;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode_writeln extends FuncDeclNode_Core {
    // TODO: maybe use puts instead of printf (no format required)
    public FuncDeclNode_writeln() {
        super("writeln", VoidTypeNode.VoidNode);
        m_bCustomCallLogic = true;
        m_bInline = true;
    }
    
    @Override
    public FuncDeclNode ValidateCall(FuncCallNode callNode) {
        // Allows any amount of parameters, as long as they are primitive/can get serialized
        // Compare given parameters to primitive types
        for (AbstractSyntaxTree param : callNode.GetParameterList()) {
            TypeNode CallParamType = param.GetType();
            // if (!NamedTypeNode.IsPrimitiveType(CallParamType, false)) {
            if (!(CallParamType instanceof PrimitiveTypeNode || PointerTypeNode.CharPointerNode.CompareType(CallParamType) || ArrayTypeNode.CharArrayNode.CompareType(CallParamType))) {
                throw new TypeCheckException(this, "writeln only supports primitive types or charptr/chararray(strings)");
            }

            // TODO: check for tostring method
            //  signature: ([type]) -> char*
        }

        return this;
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        // TODO: only one element with a single character? -> use "putchar"
        // TODO: add constants directly to the placeholder string, instead of adding a parameter

        StringBuilder builder = new StringBuilder();
        List<ParamContainer> content = new ArrayList<>();

        for (AbstractSyntaxTree element : callNode.GetParameterList()) {
            ParamContainer elementContainer = element.CreateSnippet(slave);

            TypeNode elementType = element.GetType();
            if (elementType instanceof ArrayTypeNode) {
                // handle string array type
                elementContainer = slave.CreateArrayElementPtr(elementContainer, 0);
            } else {
                // handle primitive types + string pointer type
                elementContainer = AccessInterface.TryLoadValue(slave, element, elementContainer);

                if (elementType.CompareType(PrimitiveTypeNode.BoolNode)) {
                    elementContainer = slave.ExtendToInt(elementContainer);
                }

                // some primitive types require additional conversion
                if (elementType instanceof PrimitiveTypeNode) {
                    if (((PrimitiveTypeNode) elementType).GetTypeIsDezimal() && ((PrimitiveTypeNode) elementType).GetTypeSize() != 64) {
                        // convert all decimals to double
                        elementContainer = slave.ExtendFloatToDouble(elementContainer);
                    }
                }
            }

            // Add parameter to function call
            content.add(elementContainer);

            // Add placeholder element for parameter to string
            builder.append(elementType.GetTypePlaceholder());
        }
        // Add newline (must be added as hex (\0A instead of \n, counts as a single character))
        builder.append("\n");

        //
        ParamContainer constant = slave.CreateStringConstant(builder.toString());
        ParamContainer placeholderRef = slave.CreateArrayElementPtr(constant, new ValueWrapper_Constant("0"));
        slave.CreateNativeCall(new NativeFunction_printf(placeholderRef, content));

        return null;
    }
}
