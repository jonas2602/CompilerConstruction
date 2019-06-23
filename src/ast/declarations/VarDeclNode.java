package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class VarDeclNode extends AbstractSyntaxTree {
    protected String m_Name;
    protected TypeNode m_Type;

    protected int m_ScopeIndex = -1;

    public VarDeclNode(String InName, TypeNode InType) {
        this.m_Name = InName;
        this.m_Type = InType;
        m_Type.SetParent(this);
    }

    public String GetName() {
        return m_Name;
    }
    public int GetScopeIndex() { return m_ScopeIndex; }
    public boolean HasScopeIndex() { return m_ScopeIndex != -1; }
    // public int GetOrCreateScopeIndex() {
    //     if(!HasScopeIndex()){
    //
    //     }
    //
    //     return m_ScopeIndex;
    // }


    @Override
    public TypeNode CheckType() {
        m_Type.CheckType();
        return null;
    }

    @Override
    public TypeNode GetType() {
        return m_Type.GetType();
    }
}
