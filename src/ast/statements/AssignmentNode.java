package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.operators.Operator;
import ast.declarations.ConstDeclNode;
import ast.declarations.VarDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode;
import ast.types.TypeNode;
import writer.*;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Array;
import writer.wrappers.TypeWrapper_Struct;

public class AssignmentNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Variable;
    private AbstractSyntaxTree m_Expression;
    private FuncCallNode m_FuncCallNode;

    public AssignmentNode(AbstractSyntaxTree variable, AbstractSyntaxTree expression) {
        this.m_Variable = variable;
        this.m_Expression = expression;

        m_Variable.SetParent(this);
        m_Expression.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        // TODO: integrate constant into type system
        // make shure used variable isn't a constant
        TypeNode varType = m_Variable.CheckType();
        VarDeclNode varDecl = ((AccessInterface) m_Variable).GetVarDeclNode();
        if (varDecl instanceof ConstDeclNode) {
            throw new TypeCheckException(this, "Can't assign value to a const variable");
        }

        // Only Equal types are allowed, implicit conversion is not possible
        // even primitive conversion int->real, real->int is not possible (yet)
        TypeNode expType = m_Expression.CheckType();
        if (varType.CompareType(expType)) {
            // Types are arrays?
            if (varType instanceof ArrayTypeNode) {
                int varSize = ((ArrayTypeNode) varType).GetSize();
                int expSize = ((ArrayTypeNode) expType).GetSize();
                if (varSize != expSize) {
                    throw new TypeCheckException(this, "Arrays can only be copied if the target array is the same size as the source array");
                }
            }

            return null;
        }

        // "operator:=" is defined for the given parameters?
        m_FuncCallNode = new FuncCallNode(Operator.AGN);
        m_FuncCallNode.AddParameter(m_Variable);
        m_FuncCallNode.AddParameter(m_Expression);
        m_FuncCallNode.SetParent(this);
        m_FuncCallNode.CheckType();

        // Assignment is defined to not return a type.
        // In contrast to java,... a = b = 2; is not possible
        // throw new TypeCheckException(this, "Assignment of " + expType + " to " + varType + " is not allowed");
        return null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_FuncCallNode != null) {
            return m_FuncCallNode.CreateSnippet(slave);
        }

        ParamContainer exp = m_Expression.CreateSnippet(slave);
        ParamContainer varAccess = m_Variable.CreateSnippet(slave);

        if (varAccess.IsPointer() && varAccess.GetRootType().GetChild() instanceof TypeWrapper_Array) {
            slave.CopyMemory(exp, varAccess);
        } else if (varAccess.IsPointer() && varAccess.GetRootType().GetChild() instanceof TypeWrapper_Struct) {
            slave.CopyMemory(exp, varAccess);
        } else {
            // if the expression on the right of the assignment is not a constant (variable access stuff)
            // 'exp' will contain a pointer to the requested value that must be loaded before writing
            exp = AccessInterface.TryLoadValue(slave, m_Expression, exp);

            slave.StoreInVariable(varAccess, exp);
        }

        return null;
    }
}
