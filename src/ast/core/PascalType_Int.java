package ast.core;

import ast.EPrimitiveType;
import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;

public class PascalType_Int extends PascalType_Primitive {
    public PascalType_Int() {
        super(EPrimitiveType.INT, 32, false, "%d", "0");
    }

//    public interface IntFunctionCalls {
//        public int createFunctionCall(GeneratorSlave slave, CodeSnippet_Base lParam, CodeSnippet_Base rParam);
//    }
//
//    public static abstract class FuncDeclNode_IntParam extends FuncDeclNode_Core {
//
//        private IntFunctionCalls operation;
//
//        public FuncDeclNode_IntParam(String InName, TypeNode InReturnType, PrimitiveTypeNode rparam, IntFunctionCalls operation) {
//            super(InName, InReturnType);
//
//            AddParameter(new ParamDeclNode("left", PrimitiveTypeNode.IntNode));
//            AddParameter(new ParamDeclNode("right", rparam));
//
//            m_bCustomCallLogic = true;
//            m_bInline = true;
//
//            this.operation = operation;
//        }
//
//        @Override
//        public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
//            CodeSnippet_Base leftParam = callNode.GetParameterList().get(0).CreateSnippet(slave, ctx);
//            CodeSnippet_Base rightParam = callNode.GetParameterList().get(1).CreateSnippet(slave, ctx);
//
//            int scopeIndex =  operation.createFunctionCall(slave, leftParam, rightParam);
//            return new CodeSnippet_Plain("%" + scopeIndex);
//        }
//    }
//
//    public static class FuncDeclNode_addInt extends FuncDeclNode_IntParam {
//        public FuncDeclNode_addInt() {
//            super("operator+", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> slave.AddIntInt(lParam.Write(), rParam.Write()));
//        }
//    }
//
//    public static class FuncDeclNode_addFloat extends FuncDeclNode_IntParam {
//        public FuncDeclNode_addFloat() {
//            super("operator+", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> slave.AddFloatInt(lParam.Write(), rParam.Write()));
//        }
//    }
//
//    public static class FuncDeclNode_subInt extends FuncDeclNode_IntParam {
//        public FuncDeclNode_subInt() {
//            super("operator-", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> slave.SubIntInt(lParam.Write(), rParam.Write()));
//        }
//    }
//
//    public static class FuncDeclNode_subFloat extends FuncDeclNode_IntParam {
//        public FuncDeclNode_subFloat() {
//            super("operator-", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
//                int cast = slave.CastIntToFloat(lParam.Write());
//                return slave.SubFloatFloat("%" + cast, rParam.Write());
//            });
//        }
//    }
//
//    public static class FuncDeclNode_mulInt extends FuncDeclNode_IntParam {
//        public FuncDeclNode_mulInt() {
//            super("operator*", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> slave.MulIntInt(lParam.Write(), rParam.Write()));
//        }
//    }
//
//    public static class FuncDeclNode_mulFloat extends FuncDeclNode_IntParam {
//        public FuncDeclNode_mulFloat() {
//            super("operator*", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
//                int cast = slave.CastIntToFloat(rParam.Write());
//                return slave.MulFloatFloat(lParam.Write(),"%" + cast);
//            });
//        }
//    }
//
//    public static class FuncDeclNode_divInt extends FuncDeclNode_IntParam {
//        public FuncDeclNode_divInt() {
//            super("operator/", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> {
//                int castLeft = slave.CastIntToFloat(lParam.Write());
//                int castRight = slave.CastIntToFloat(rParam.Write());
//                return slave.DivFloatFloat("%" + castLeft, "%" + castRight);
//            });
//        }
//    }
//
//    public static class FuncDeclNode_divFloat extends FuncDeclNode_IntParam {
//        public FuncDeclNode_divFloat() {
//            super("operator/", PrimitiveTypeNode.FloatNode, PrimitiveTypeNode.FloatNode, (slave, lParam, rParam) -> {
//                int cast = slave.CastIntToFloat(lParam.Write());
//                return slave.DivFloatFloat("%" + cast, rParam.Write());
//            });
//        }
//    }
//
//    public static class FuncDeclNode_divTrucInt extends FuncDeclNode_IntParam {
//        public FuncDeclNode_divTrucInt() {
//            super("operatordiv", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> slave.DivIntInt(lParam.Write(), rParam.Write()));
//        }
//    }
//
//    public static class FuncDeclNode_modInt extends FuncDeclNode_IntParam {
//        public FuncDeclNode_modInt() {
//            super("operatormod", PrimitiveTypeNode.IntNode, PrimitiveTypeNode.IntNode, (slave, lParam, rParam) -> slave.ModIntInt(lParam.Write(), rParam.Write()));
//        }
//    }
}
