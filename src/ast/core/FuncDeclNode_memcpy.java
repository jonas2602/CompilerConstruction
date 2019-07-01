package ast.core;

import ast.types.NamedTypeNode;
import llvm.CodeSnippet_FuncDecl;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class FuncDeclNode_memcpy extends FuncDeclNode_Core {

    public FuncDeclNode_memcpy() {
        super("llvm.memcpy.p0i8.p0i8.i64",  NamedTypeNode.VoidNode);
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
}
