package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

public class ConstDeclNode extends VarDeclNode {
    private AbstractSyntaxTree m_Constant;

    // TODO: implement const into type system
    //  nonconst := const           (valid)
    //  const := nonconst           (invalid)
    //  const ptr := nonconst ptr   (invalid)
    //  const ptr := const ptr      (valid to initialize)
    //  func(x)     | call(const)   (valid)
    //  func(var x) | call(const)   (invalid)
    //  func(var const x) | call(const)   (valid)
    public ConstDeclNode(String name, AbstractSyntaxTree constant) {
        // Assuming that in types are always constant
        super(name, (TypeNode) constant.GetType().Copy());

        m_Constant = constant;
        m_Constant.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        if (!(m_Constant instanceof ConstantNode)) {
            //throw new TypeCheckException(this, "Constant can not get initialized with a variable right now");
            SetType(m_Constant.GetType());
        }

        m_Constant.CheckType();
        return null;
    }

    @Override
    public TypeNode GetType() {
        return m_Constant.GetType();
    }

    public AbstractSyntaxTree GetConstant() {
        return m_Constant;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return m_Constant.CreateSnippet(slave);
    }
}
