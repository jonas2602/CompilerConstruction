package ast.declarations;

import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import writer.GeneratorSlave;

public class ParamDeclNode extends VarDeclNode {
    public ParamDeclNode(String InName, TypeNode InType) {
        super(InName, InType);
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return m_Type.CreateSnippet(slave, ctx);
    }
}
