package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

public class VarDeclNode extends AbstractSyntaxTree {
    protected String m_Name;
    protected TypeNode m_TypeNode;

    protected ParamContainer m_ScopeContainer = null;

    private boolean m_GlobalVariable;

    public VarDeclNode(String name) {
        m_Name = name;
        m_GlobalVariable = false;
    }

    public VarDeclNode(String name, TypeNode type) {
        this(name);
        SetType(type);
    }

    public boolean IsGlobalVariable() {
        return m_GlobalVariable;
    }

    public void SetGlobalVariable(boolean global) {
        m_GlobalVariable = global;
    }

    public String GetName() {
        return m_Name;
    }

    @Override
    public TypeNode CheckType() {
        m_TypeNode.CheckType();
        return null;
    }

    public void SetType(TypeNode type) {
        m_TypeNode = type;
        m_TypeNode.SetParent(this);
    }

    @Override
    public TypeNode GetType() {
        return m_TypeNode.GetType();
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_ScopeContainer == null) {
            if (m_GlobalVariable) {
                TypeWrapper wrappedType = m_TypeNode.GetWrappedType();
                m_ScopeContainer = slave.AllocateGlobalMemory(wrappedType, m_TypeNode.GetDefaultValue());
            } else {
                // Allocate memory for the variable
                TypeWrapper wrappedType = m_TypeNode.GetWrappedType();
                m_ScopeContainer = slave.AllocateMemory(wrappedType);

                // store default value
                m_TypeNode.InitVariable(slave, m_ScopeContainer);
            }
        }

        return m_ScopeContainer;
    }
}
