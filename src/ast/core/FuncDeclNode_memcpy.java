package ast.core;

import ast.TypeCheckException;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode;
import ast.types.NamedTypeNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_FuncDecl;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class FuncDeclNode_memcpy extends FuncDeclNode_Core {

    public FuncDeclNode_memcpy() {
        super("llvm.memcpy.p0i8.p0i8.i64", NamedTypeNode.VoidNode);
        m_bCustomCallLogic = true;
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

        int varSize = ((ArrayTypeNode) varType).GetSize();
        int expSize = ((ArrayTypeNode) expType).GetSize();
        if (varSize < expSize) {
            throw new TypeCheckException(this, "Arrays can only get copied if the target array is larger or has equal size to the source array");
        }

        return true;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // Only build once
        if (m_IsCreated) return null;
        m_IsCreated = true;

        CodeSnippet_FuncDecl decl = slave.CreateFunctionDeclaration("llvm.memcpy.p0i8.p0i8.i64", new CodeSnippet_Plain("void"));
        decl.AddParameter(new CodeSnippet_Plain("i8* nocapture writeonly"));
        decl.AddParameter(new CodeSnippet_Plain("i8* nocapture readonly"));
        decl.AddParameter(new CodeSnippet_Plain("i64"));
        decl.AddParameter(new CodeSnippet_Plain("i1"));

        return null;
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer exp = callNode.GetParameter(1).CreateSnippet(slave);
        ParamContainer varAccess = callNode.GetParameter(0).CreateSnippet(slave);
        slave.CopyMemory(exp, varAccess);

        return null;
    }
}
