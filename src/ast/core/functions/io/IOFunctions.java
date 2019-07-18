package ast.core.functions.io;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.core.operators.Operator;
import ast.declarations.FuncDeclNode;
import ast.declarations.VarDeclNode;
import ast.expressions.*;
import ast.statements.AssignmentNode;
import ast.statements.BranchNode;
import ast.statements.CompStmtNode;
import ast.statements.ForNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.natives.io.NativeFunction_sprintf;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;
import writer.wrappers.TypeWrapper_Array;
import writer.wrappers.TypeWrapper_Primitive;

import java.lang.reflect.Array;

public class IOFunctions implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new FuncDeclNode_readln());
        std.AddFunctionDeclaration(new FuncDeclNode_writeln());
        std.AddFunctionDeclaration(new FuncDeclNode_write());

        std.AddFunctionDeclaration(new FuncDeclNode_ToString_Integer());
        std.AddFunctionDeclaration(new FuncDeclNode_ToString_Boolean());
        std.AddFunctionDeclaration(new FuncDeclNode_ToString_Array());
        std.AddFunctionDeclaration(new FuncDeclNode_ToString_List());
    }

    public static class FuncDeclNode_ToString_Array extends FuncDeclNode_Core {
        public FuncDeclNode_ToString_Array() {
            super("tostring", PointerTypeNode.CharPointerNode);

            AddParameter("arr", ArrayTypeNode.WildcardArrayNode());
        }

        @Override
        public FuncDeclNode ValidateCall(FuncCallNode callNode) {
            FuncDeclNode outDeclNode = super.ValidateCall(callNode);
            if (outDeclNode != null) {
                // special case: strings / char[]
                TypeNode arrType = callNode.GetParameterType(0);
                if (ArrayTypeNode.CharArrayNode.CompareType(arrType)) {
                    outDeclNode = new FuncDeclNode_ToString_CharArray();
                }
            }

            return outDeclNode;
        }
    }

    public static class FuncDeclNode_ToString_List extends FuncDeclNode_Core {
        public FuncDeclNode_ToString_List() {
            super("tostring", PointerTypeNode.CharPointerNode);

            AddParameter("arr", new ArrayTypeNode_Dynamic(PrimitiveTypeNode.IntNode));

            CompStmtNode compStmt = new CompStmtNode();
            AssignmentNode assignFirst = new AssignmentNode(new AccessNode_Variable("tostring"), new ConstantNode_String("["));

            m_Block.AddVariableDeclaration("i", PrimitiveTypeNode.IntNode);

            // append delimiter if i > 0
            FuncCallNode appendDelim = new FuncCallNode(Operator.ADD, new AccessNode_Variable("tostring"), new ConstantNode_String(", "));
            AssignmentNode assignDelim = new AssignmentNode(new AccessNode_Variable("tostring"), appendDelim);
            BranchNode delimBranch = new BranchNode(new FuncCallNode(Operator.GT, new AccessNode_Variable("i"), ConstantNode.IntNode(0)), assignDelim);

            // append next element
            FuncCallNode elemStrCall = new FuncCallNode("tostring", new AccessNode_Array(new AccessNode_Variable("arr"), new AccessNode_Variable("i")));
            FuncCallNode appendElem = new FuncCallNode(Operator.ADD, new AccessNode_Variable("tostring"), elemStrCall);
            AssignmentNode assignElem = new AssignmentNode(new AccessNode_Variable("tostring"), appendElem);

            CompStmtNode forBody = new CompStmtNode(delimBranch, assignElem);
            ForNode forStmt = new ForNode(new AccessNode_Variable("i"), ConstantNode.IntNode(0), new FuncCallNode("high", new AccessNode_Variable("arr")), true, forBody);

            FuncCallNode appendLast = new FuncCallNode(Operator.ADD, new AccessNode_Variable("tostring"), new ConstantNode_String("]"));
            AssignmentNode assignLast = new AssignmentNode(new AccessNode_Variable("tostring"), appendLast);

            compStmt.AddStatement(assignFirst);
            compStmt.AddStatement(forStmt);
            compStmt.AddStatement(assignLast);
            m_Block.SetCompoundStatement(compStmt);
        }

        @Override
        public FuncDeclNode ValidateCall(FuncCallNode callNode) {
            return super.ValidateCall(callNode);
        }
    }

    public static class FuncDeclNode_ToString_CharArray extends FuncDeclNode_Core {
        public FuncDeclNode_ToString_CharArray() {
            super("tostring", PointerTypeNode.CharPointerNode);

            m_bInline = true;
            m_bCustomCallLogic = true;

            AddParameter("arr", ArrayTypeNode.CharArrayNode);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer arrParam = callNode.GetParameter(0).CreateSnippet(slave);
            return slave.CreateArrayElementPtr(arrParam, 0);
        }
    }

    public static class FuncDeclNode_ToString_Integer extends FuncDeclNode_Core {
        public FuncDeclNode_ToString_Integer() {
            super("tostring", PointerTypeNode.CharPointerNode);

            m_bInline = true;
            m_bCustomCallLogic = true;

            AddParameter("i", PrimitiveTypeNode.IntNode);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer buffer = slave.AllocateMemory(new TypeWrapper_Array(TypeWrapper_Primitive.CHAR, 12));
            buffer = slave.CreateArrayElementPtr(buffer, 0);
            ParamContainer format = slave.CreateStringConstant("%d");
            format = slave.CreateArrayElementPtr(format, 0);
            ParamContainer value = callNode.GetParameter(0).CreateSnippet(slave);
            value = AccessInterface.TryLoadValue(slave, callNode.GetParameter(0), value);
            slave.CreateNativeCall(new NativeFunction_sprintf(buffer, format, value));

            return buffer;
        }
    }

    public static class FuncDeclNode_ToString_Boolean extends FuncDeclNode_Core {
        public FuncDeclNode_ToString_Boolean() {
            super("tostring", PointerTypeNode.CharPointerNode);

            m_bInline = true;
            m_bCustomCallLogic = true;

            AddParameter("i", PrimitiveTypeNode.BoolNode);
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            ParamContainer boolParam = callNode.GetParameter(0).CreateSnippet(slave);
            boolParam = AccessInterface.TryLoadValue(slave, callNode.GetParameter(0), boolParam);
            ParamContainer trueParam = slave.CreateStringConstant("TRUE");
            trueParam = slave.CreateArrayElementPtr(trueParam, 0);
            ParamContainer falseParam = slave.CreateStringConstant("FALSE");
            falseParam = slave.CreateArrayElementPtr(falseParam, 0);

            return slave.CreateTernaryOperator(boolParam, trueParam, falseParam);
        }
    }
}
