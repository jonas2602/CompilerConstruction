package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.TypeWrapper;

public class VarDeclNode extends AbstractSyntaxTree {
    protected String m_Name;
    protected TypeNode m_TypeNode;

    protected ParamContainer m_ScopeContainer = null;

    private boolean m_GlobalVariable;

    public VarDeclNode(String name, TypeNode type) {
        m_Name = name;
        m_TypeNode = type;
        m_TypeNode.SetParent(this);
        m_GlobalVariable = false;
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

    @Override
    public TypeNode GetType() {
        return m_TypeNode.GetType();
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_ScopeContainer == null) {
            if(m_GlobalVariable) {
                TypeWrapper wrappedType = m_TypeNode.GetWrappedType();
                m_ScopeContainer = slave.AllocateGlobalMemory(wrappedType, m_TypeNode.GetDefaultValue());
            }
            else {
                // Allocate memory for the variable
                TypeWrapper wrappedType = m_TypeNode.GetWrappedType();
                m_ScopeContainer = slave.AllocateMemory(wrappedType);

                // store default value
                ParamContainer defaultValue = m_TypeNode.GetDefaultValue();
                if (defaultValue != null) {
                    slave.StoreInVariable(m_ScopeContainer, defaultValue);
                }
            }
        }

        return m_ScopeContainer;
    }
}
