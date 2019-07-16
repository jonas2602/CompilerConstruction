package ast.core.operators;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.functions.list.List;
import ast.core.functions.math.Math;
import ast.core.functions.memory.Memory;
import ast.declarations.FuncDeclNode;
import ast.expressions.*;
import ast.types.ArrayTypeNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.VoidTypeNode;
import writer.GeneratorSlave;
import writer.natives.memory.NativeFunction_malloc;
import writer.natives.string.NativeFunction_strcat;
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
        std.AddFunctionDeclaration(new AddString());
        std.AddFunctionDeclaration(new AddStringPtr());
    }

    public static class StringLength extends FuncDeclNode_Core {
        public StringLength() {
            super(Operator.LENGTH, PrimitiveTypeNode.IntNode);

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
            super(Operator.LENGTH, PrimitiveTypeNode.IntNode);

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

            FuncCallNode staticLength = new FuncCallNode(Operator.HIGH, strAccess);
            staticLength = new FuncCallNode(Operator.ADD, staticLength, ConstantNode.IntNode(1));
            FuncCallNode dynamicLength = new FuncCallNode(List.GETLEN, ptrAccess);
            dynamicLength = new FuncCallNode(Operator.ADD, dynamicLength, ConstantNode.IntNode(1));
            FuncCallNode minCall = new FuncCallNode(Math.MIN, staticLength, dynamicLength);

            AccessNode_Array firstChar = new AccessNode_Array(strAccess, ConstantNode.IntNode(0));
            AccessNode_Address charptr = new AccessNode_Address(firstChar);
            FuncCallNode cpyCall = new FuncCallNode(Memory.MOVE, charptr, ptrAccess, minCall);

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
            charParam = AccessInterface.TryLoadValue(slave, callNode.GetParameter(1), charParam);

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

    public static class AddStringPtr extends FuncDeclNode_Core {
        public AddStringPtr() {
            super(Operator.ADD, PointerTypeNode.CharPointerNode);

            AddParameter("str1", ArrayTypeNode.CharArrayNode);
            AddParameter("str2", PointerTypeNode.CharPointerNode);

            m_bInline = true;
            m_bCustomCallLogic = true;
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer str1Param = callNode.GetParameter(0).CreateSnippet(slave);
            ParamContainer str1Ptr = slave.CreateArrayElementPtr(str1Param, 0);
            ParamContainer str2Ptr = callNode.GetParameter(1).CreateSnippet(slave);

            return StringOperators.buildCat(slave, str1Ptr, str2Ptr);
        }
    }

    public static class AddString extends FuncDeclNode_Core {
        public AddString() {
            super(Operator.ADD, PointerTypeNode.CharPointerNode);

            AddParameter("str1", ArrayTypeNode.CharArrayNode);
            AddParameter("str2", ArrayTypeNode.CharArrayNode);

            m_bInline = true;
            m_bCustomCallLogic = true;
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer str1Param = callNode.GetParameter(0).CreateSnippet(slave);
            ParamContainer str1Ptr = slave.CreateArrayElementPtr(str1Param, 0);
            ParamContainer str2Param = callNode.GetParameter(1).CreateSnippet(slave);
            ParamContainer str2Ptr = slave.CreateArrayElementPtr(str2Param, 0);

            return StringOperators.buildCat(slave, str1Ptr, str2Ptr);
        }
    }

    private static ParamContainer buildCat(GeneratorSlave slave, ParamContainer str1Ptr, ParamContainer str2Ptr) {
        // get length of source string
        ParamContainer str1Len = slave.CreateNativeCall(new NativeFunction_strlen(str1Ptr));
        ParamContainer str2Len = slave.CreateNativeCall(new NativeFunction_strlen(str1Ptr));
        ParamContainer newSize = slave.AddIntInt(str1Len, str2Len);
        ParamContainer newSizeNull = slave.AddIntInt(newSize, ParamContainer.INTCONTAINER(1));

        // allocate new momory with size + char + \0
        ParamContainer newMem = slave.CreateNativeCall(new NativeFunction_malloc(newSizeNull));
        newMem = slave.BitCast(newMem, TypeWrapper_Pointer.CHARPTR);

        // Copy source string to the new memory
        slave.CreateNativeCall(new NativeFunction_strcpy(newMem, str1Ptr));

        // Copy other string behind it
        slave.CreateNativeCall(new NativeFunction_strcat(newMem, str2Ptr));

        return newMem;
    }
}
