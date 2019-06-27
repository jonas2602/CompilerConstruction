package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.VarDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.ConstantNode;
import ast.expressions.VariableNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import writer.GeneratorSlave;
import writer.TypeContainer;

public class AssignmentNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Variable;
    private AbstractSyntaxTree m_Expression;

    public AssignmentNode(AbstractSyntaxTree InVariable, AbstractSyntaxTree InExpression) {
        this.m_Variable = InVariable;
        this.m_Expression = InExpression;

        m_Variable.SetParent(this);
        m_Expression.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        // Only Equal types are allowed, implicit conversion is not possible
        // even primitive conversion int->real, real->int is not possible
        TypeNode varType = m_Variable.CheckType();
        TypeNode expType = m_Expression.CheckType();
        if (!varType.CompareType(expType)) {
            throw new TypeCheckException(this, "Assignment of " + expType + " to " + varType + " is not allowed");
        }

        // Assignment is defined to not return a type.
        // In contrast to java,... a = b = 2; is not possible
        return null;
    }

    // @Override
    // public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
    //     CodeSnippet_Base exp = m_Expression.CreateSnippet(slave, ctx);

    //     // TODO: Arrays, Records, ...?
    //     VarDeclNode varDecl = ((VariableNode) m_Variable).GetDeclaration();

    //     // Is expression a constant?
    //     if (m_Expression instanceof ConstantNode) {
    //         int LocalIndex = slave.AllocateInt();
    //         slave.StoreInt(exp.Write(), LocalIndex);
    //         varDecl.SetScopeIndex(LocalIndex);
    //     } else {
    //         // assign local index to variable
    //         varDecl.SetScopeIndex(Integer.parseInt(exp.Write().substring(1)));
    //     }

    //     return exp;
    // }

    @Override
    public TypeContainer CreateSnippet(GeneratorSlave slave) {
        TypeContainer exp = m_Expression.CreateSnippet(slave);
        TypeContainer varAccess = m_Variable.CreateSnippet(slave);

        // if the expression on the right of the assignment is not a constant (variable access stuff)
        // 'exp' will contain a pointer to the requested value that must be loaded before writing
        if (m_Expression instanceof AccessInterface) {
            exp = slave.LoadFromVariable(exp);
        }

        slave.StoreInVariable(varAccess, exp);
        return null;
    }
}
