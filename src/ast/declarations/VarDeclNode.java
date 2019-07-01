package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.TypeWrapper;

public class VarDeclNode extends AbstractSyntaxTree {
    protected String m_Name;
    protected TypeNode m_Type;

    protected int m_ScopeIndex = -1;
    protected ParamContainer m_ScopeContainer = null;

    public VarDeclNode(String InName, TypeNode InType) {
        this.m_Name = InName;
        this.m_Type = InType;
        m_Type.SetParent(this);
    }

    public String GetName() {
        return m_Name;
    }

    public int GetScopeIndex() {
        return m_ScopeIndex;
    }

    public void SetScopeIndex(int InScopeIndex) {
        m_ScopeIndex = InScopeIndex;
    }

    public boolean HasScopeIndex() {
        return m_ScopeIndex != -1;
    }


    @Override
    public TypeNode CheckType() {
        m_Type.CheckType();
        return null;
    }

    @Override
    public TypeNode GetType() {
        return m_Type.GetType();
    }

    public void SetType(TypeNode type) {
        m_Type = type;
    }

    public TypeNode GetRawType() { return m_Type;}

//    @Override
//    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
//        TypeWrapper wrappedType = m_Type.GetWrappedType();
//        ParamContainer outContainer = slave.AllocateMemory(wrappedType);
//
//        return null;
//    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_ScopeContainer == null) {
            // Allocate memory for the variable
            TypeWrapper wrappedType = m_Type.GetWrappedType();
            m_ScopeContainer = slave.AllocateMemory(wrappedType);

            // store default value
            ParamContainer defaultValue = m_Type.GetDefaultValue();
            if (defaultValue != null) {
                slave.StoreInVariable(m_ScopeContainer, defaultValue);
            }
        }

        return m_ScopeContainer;
    }
}
