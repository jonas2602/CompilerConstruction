package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.NilTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.snippets.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class ConstantNode extends AbstractSyntaxTree {
    public static final ConstantNode NilNode = new ConstantNode("null", new NilTypeNode());

    public static ConstantNode IntNode(int i) {
        return new ConstantNode(Integer.toString(i), PrimitiveTypeNode.IntNode);
    }

    public static ConstantNode CharNode(char c) {
        return new ConstantNode(Integer.toString((int) c), PrimitiveTypeNode.CharNode);
    }

    public static ConstantNode BoolNode(boolean b) {
        return new ConstantNode(b ? Integer.toString(1) : Integer.toString(0), PrimitiveTypeNode.BoolNode);
    }


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
