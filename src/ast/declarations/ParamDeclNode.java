package ast.declarations;

import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import writer.GeneratorSlave;

public class ParamDeclNode extends VarDeclNode {
    private int m_ParamIndex;

    public ParamDeclNode(String InName, TypeNode InType, int InIndex) {
        super(InName, InType);
        m_ParamIndex = InIndex;
    }

    int GetIndex() {
        return m_ParamIndex;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return m_Type.CreateSnippet(slave, ctx);
    }
}
