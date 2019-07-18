package ast.core.functions.string;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.functions.array.ArrayFunction;
import ast.core.operators.Operator;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.natives.string.NativeFunction_strlen;
import writer.wrappers.ParamContainer;

public class StringFunctions implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new StringLength());
        std.AddFunctionDeclaration(new CharPtrLength());
    }

    public static class StringLength extends FuncDeclNode_Core {
        public StringLength() {
            super(ArrayFunction.LENGTH, PrimitiveTypeNode.IntNode);

            m_bInline = true;
            m_bCustomCallLogic = true;

            AddParameter("str", ArrayTypeNode.CharArrayNode);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer strArrParam = callNode.GetParameter(0).CreateSnippet(slave);
            ParamContainer strStart = slave.CreateArrayElementPtr(strArrParam, 0);

            ParamContainer outLong = slave.CreateNativeCall(new NativeFunction_strlen(strStart));
            return slave.TruncToInt(outLong);
        }
    }

    public static class CharPtrLength extends FuncDeclNode_Core {
        public CharPtrLength() {
            super(ArrayFunction.LENGTH, PrimitiveTypeNode.IntNode);

            m_bInline = true;
            m_bCustomCallLogic = true;

            AddParameter("str", PointerTypeNode.CharPointerNode);
        }

        @Override
        public FuncDeclNode ValidateCall(FuncCallNode callNode) {
            return super.ValidateCall(callNode);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer charPtrParam = callNode.GetParameter(0).CreateSnippet(slave);

            ParamContainer outLong = slave.CreateNativeCall(new NativeFunction_strlen(charPtrParam));
            return slave.TruncToInt(outLong);
        }
    }
}
