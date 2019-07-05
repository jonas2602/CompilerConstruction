package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

public class TypeDeclNode extends AbstractSyntaxTree {
    private String m_Name;
    private TypeNode m_Type;

    public TypeDeclNode(String name, TypeNode type) {
        m_Name = name;
        m_Type = type;
        m_Type.SetTypeName(name);
        m_Type.SetParent(this);
    }

    public String GetName() {
        return m_Name;
    }

    @Override
    public TypeNode CheckType() {
        m_Type.CheckType();
        return null;
    }

    @Override
    public TypeNode GetType() {
        return m_Type;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return m_Type.CreateSnippet(slave);
    }
}
