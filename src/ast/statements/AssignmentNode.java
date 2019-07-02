package ast.statements;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.core.operators.Operator;
import ast.declarations.FuncDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.ArrayTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.TypeWrapper_Array;

import java.util.List;

public class AssignmentNode extends AbstractSyntaxTree {
    private AbstractSyntaxTree m_Variable;
    private AbstractSyntaxTree m_Expression;
    private FuncCallNode m_FuncCallNode;

    private FuncCallNode m_Function;

    public AssignmentNode(AbstractSyntaxTree variable, AbstractSyntaxTree expression) {
        this.m_Variable = variable;
        this.m_Expression = expression;

        m_Variable.SetParent(this);
        m_Expression.SetParent(this);
        m_Function = null;
    }

    @Override
    public TypeNode CheckType() {
        // Only Equal types are allowed, implicit conversion is not possible
        // even primitive conversion int->real, real->int is not possible (yet)
        TypeNode varType = m_Variable.CheckType();
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

    // @Override
    // public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
    //     CodeSnippet_Base exp = m_Expression.CreateSnippet(slave, ctx);

    //     // TODO: Arrays, Records, ...?
    //     VarDeclNode varDecl = ((AccessNode_Variable) m_Variable).GetDeclaration();

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
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if(m_FuncCallNode != null){
            return m_FuncCallNode.CreateSnippet(slave);
        }

        ParamContainer exp = m_Expression.CreateSnippet(slave);
        ParamContainer varAccess = m_Variable.CreateSnippet(slave);

        if (varAccess.IsPointer() && varAccess.GetRootType().GetChild() instanceof TypeWrapper_Array) {
            FuncDeclNode memcpy = GetOwningBlock().GetFunctionDeclaration("llvm.memcpy.p0i8.p0i8.i64").get(0);
            memcpy.CreateSnippet(slave);
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
