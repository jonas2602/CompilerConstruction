package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.snippets.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

public class ConstantNode extends AbstractSyntaxTree {
    protected String m_Data;
    protected TypeNode m_TypeNode;

    public ConstantNode(String data, TypeNode type) {
        this.m_Data = data;
        this.m_TypeNode = type;

        m_TypeNode.SetParent(this);
    }

    public String GetData() {
        return m_Data;
    }

    @Override
    public TypeNode CheckType() {
        return m_TypeNode.CheckType();
    }

    @Override
    public TypeNode GetType() {
        return m_TypeNode.GetType();
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        return new CodeSnippet_Plain(m_Data);
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return new ParamContainer(m_TypeNode.GetWrappedType(), m_Data);
    }
}
