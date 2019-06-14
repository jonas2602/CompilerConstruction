package ast.declaration;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class ConstDeclNode extends AbstractSyntaxTree {
    private String m_Name;
    private AbstractSyntaxTree m_Constant;

    public ConstDeclNode(String InName, AbstractSyntaxTree InConstant){
        this.m_Name = InName;
        this.m_Constant = InConstant;
        m_Constant.SetParent(this);
    }

    public String GetName(){
        return m_Name;
    }

    @Override
    public TypeNode CheckType() {
        m_Constant.CheckType();
        return null;
    }
}
