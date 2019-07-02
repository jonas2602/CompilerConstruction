package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class ConstantNode extends AbstractSyntaxTree {
    protected String m_Data;
    protected TypeNode m_Type;

    public ConstantNode(String data, TypeNode type) {
        this.m_Data = data;
        this.m_Type = type;

        m_Type.SetParent(this);
    }

    public String GetData() {
        return m_Data;
    }

    @Override
    public TypeNode CheckType() {
        return m_Type.CheckType();
    }

    @Override
    public TypeNode GetType() {
        return m_Type.GetType();
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return new CodeSnippet_Plain(m_Data);
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return new ParamContainer(m_Type.GetWrappedType(), m_Data);
    }
}
