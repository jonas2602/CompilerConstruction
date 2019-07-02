package ast.core.operators;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class GenericOperators implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        //prefix operators
        std.AddFunctionDeclaration(new AssignGenericPrimPrim());
        std.AddFunctionDeclaration(new AssignGenericArrayArray());
    }

    public static class AssignGenericPrimPrim extends FuncDeclNode_Core {
        public AssignGenericPrimPrim() {
            super(Operator.AGN, NamedTypeNode.VoidNode);

            m_bCustomCallLogic = true;
            m_bInline = true;
        }

        @Override
        public boolean CompareSignature(FuncDeclNode InOther) {
            return super.CompareSignature(InOther);
        }

        @Override
        public boolean ValidateCall(FuncCallNode InCallNode) {
            if (InCallNode.GetParameterCount() != 2) {
                return false;
            }

            TypeNode varType = InCallNode.GetParameter(0).CheckType();
            TypeNode expType = InCallNode.GetParameter(1).CheckType();

            // Only for non array types
            if (varType instanceof ArrayTypeNode || expType instanceof ArrayTypeNode) {
                return false;
            }

            if (!varType.CompareType(expType)) {
                return false;
            }

            return true;
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
            AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);
            ParamContainer leftParam = lParam.CreateSnippet(slave);
            ParamContainer rightParam = rParam.CreateSnippet(slave);

            return null;
        }
    }

    public static class AssignGenericArrayArray extends FuncDeclNode_Core {
        public AssignGenericArrayArray() {
            super(Operator.AGN, NamedTypeNode.VoidNode);

            m_bCustomCallLogic = true;
            m_bInline = true;
        }

        @Override
        public boolean ValidateCall(FuncCallNode InCallNode) {
            if (InCallNode.GetParameterCount() != 2) {
                return false;
            }

            TypeNode varType = InCallNode.GetParameter(0).CheckType();
            TypeNode expType = InCallNode.GetParameter(1).CheckType();

            if (!(varType instanceof ArrayTypeNode && expType instanceof ArrayTypeNode)) {
                return false;
            }

            if (varType.CompareType(expType)) {
                // Types are arrays?
                int varSize = ((ArrayTypeNode) varType).GetSize();
                int expSize = ((ArrayTypeNode) expType).GetSize();
                if (varSize != expSize) {
                    throw new TypeCheckException(this, "Arrays can only be copied if the target array is the same size as the source array");
                }

                return true;
            }

            return false;
        }

        @Override
        public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
            AbstractSyntaxTree lParam = callNode.GetParameterList().get(0);
            AbstractSyntaxTree rParam = callNode.GetParameterList().get(1);
            ParamContainer leftParam = lParam.CreateSnippet(slave);
            ParamContainer rightParam = rParam.CreateSnippet(slave);

            return null;
        }
    }
}
