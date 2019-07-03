package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.expressions.ConstantNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.TypeWrapper;

public class ConstDeclNode extends VarDeclNode {
    private String m_Name;
    private ConstantNode m_Constant;

    // TODO: implement const into type system
    //  nonconst := const           (valid)
    //  const := nonconst           (invalid)
    //  func(x)     | call(const)   (valid)
    //  func(var x) | call(const)   (invalid)
    //  func(var const x) | call(const)   (valid)
    public ConstDeclNode(String name, AbstractSyntaxTree constant) {
        // Assuming that in types are always constant
        super(name, constant.GetType());
        m_Name = name;
        m_Constant = (ConstantNode) constant;
        m_Constant.SetParent(this);
    }

    public String GetName() {
        return m_Name;
    }

    @Override
    public TypeNode CheckType() {
        m_Constant.CheckType();
        return null;
    }

    @Override
    public TypeNode GetType() {
        return m_Constant.GetType();
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_ScopeContainer == null) {
            // Allocate memory for the variable
            TypeWrapper wrappedType = m_TypeNode.GetWrappedType();
            m_ScopeContainer = slave.AllocateMemory(wrappedType);

            // store const value
            ParamContainer constValue = m_Constant.CreateSnippet(slave);
            slave.StoreInVariable(m_ScopeContainer, constValue);
        }

        return m_ScopeContainer;
    }
}
