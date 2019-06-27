package ast.expressions;

import ast.types.NamedTypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Constant;
import writer.GeneratorSlave;
import writer.TypeContainer;

public class ConstantNode_String extends ConstantNode {
    public ConstantNode_String(String InData) {
        super(InData, NamedTypeNode.StringNode);
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        CodeSnippet_Constant strConst = slave.CreateStringConstant(m_Data);
        return slave.CreateStringReference(strConst);
    }

    @Override
    public TypeContainer CreateSnippet(GeneratorSlave slave) {
        TypeContainer strConst = slave.CreateStringConstantNew(m_Data);
        return slave.CreateArrayElementPtr(strConst, "0");
    }
}
