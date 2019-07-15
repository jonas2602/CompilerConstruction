package ast.core.operators;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.declarations.FuncDeclNode;
import ast.expressions.*;
import ast.statements.CompStmtNode;
import ast.types.ArrayTypeNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_malloc;
import writer.natives.string.NativeFunction_strcpy;
import writer.natives.string.NativeFunction_strlen;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

public class StringOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new StringLength());
        std.AddFunctionDeclaration(new CharPtrLength());
        std.AddFunctionDeclaration(new AssignCharPointerArray());
        std.AddFunctionDeclaration(new AssignCharArrayPointer());
        std.AddFunctionDeclaration(new AddChar());
    }

    public static class StringLength extends FuncDeclNode_Core {
        public StringLength() {
            super("length", PrimitiveTypeNode.IntNode);

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
            super("length", PrimitiveTypeNode.IntNode);

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


    public static class AssignCharPointerArray extends PascalType_Assignment {
        public AssignCharPointerArray() {
            super(Operator.AGN, new VoidTypeNode(), PointerTypeNode.CharPointerNode, ArrayTypeNode.CharArrayNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.BitCast(rParam, TypeWrapper_Pointer.CHARPTR);
                slave.StoreInVariable(lParam, cast);
                return null;
            });
        }
    }

    public static class AssignCharArrayPointer extends FuncDeclNode_Core {
        public AssignCharArrayPointer() {
            super(Operator.AGN, VoidTypeNode.VoidNode);

            AddParameter("str", ArrayTypeNode.CharArrayNode);
            AddParameter("ptr", PointerTypeNode.CharPointerNode);

            m_bInline = true;

            AccessNode_Variable strAccess = new AccessNode_Variable("str");
            AccessNode_Variable ptrAccess = new AccessNode_Variable("ptr");

            FuncCallNode staticLength = new FuncCallNode("high", strAccess);
            staticLength = new FuncCallNode(Operator.ADD, staticLength, ConstantNode.IntNode(1));
            FuncCallNode dynamicLength = new FuncCallNode("length", ptrAccess);
            FuncCallNode minCall = new FuncCallNode("min", staticLength, dynamicLength);

            AccessNode_Array firstChar = new AccessNode_Array(strAccess, ConstantNode.IntNode(0));
            AccessNode_Address charptr = new AccessNode_Address(firstChar);
            FuncCallNode cpyCall = new FuncCallNode("move", charptr, ptrAccess, minCall);

            m_Block.SetCompoundStatement(cpyCall);

        }
    }

    public static class AddChar extends FuncDeclNode_Core {
        public AddChar() {
            super(Operator.ADD, PointerTypeNode.CharPointerNode);

            AddParameter("str", ArrayTypeNode.CharArrayNode);
            AddParameter("chr", PrimitiveTypeNode.CharNode);

            m_bInline = true;
            m_bCustomCallLogic = true;
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer strParam = callNode.GetParameter(0).CreateSnippet(slave);
            ParamContainer strPtr = slave.CreateArrayElementPtr(strParam, 0);
            ParamContainer charParam = callNode.GetParameter(1).CreateSnippet(slave);

            // get length of source string
            ParamContainer strLen = slave.CreateNativeCall(new NativeFunction_strlen(strPtr));
            ParamContainer newSize = slave.AddIntInt(strLen, ParamContainer.INTCONTAINER(2));

            // allocate new momory with size + char + \0
            ParamContainer newMem = slave.CreateNativeCall(new NativeFunction_malloc(newSize));
            newMem = slave.BitCast(newMem, TypeWrapper_Pointer.CHARPTR);

            // Copy source string to the new memory
            slave.CreateNativeCall(new NativeFunction_strcpy(newMem, strPtr));

            // Copy char behind it
            ParamContainer charPosPtr = slave.CreatePtrArrayElementPtr(newMem, strLen);
            slave.StoreInVariable(charPosPtr, charParam);

            // set \0 at the end
            ParamContainer lastIndexParam = slave.AddIntInt(strLen, ParamContainer.INTCONTAINER(1));
            ParamContainer lastPosPtr = slave.CreatePtrArrayElementPtr(newMem, lastIndexParam);
            slave.StoreInVariable(lastPosPtr, ParamContainer.CHARCONTAINER('\0'));

            return newMem;
        }
    }


}
