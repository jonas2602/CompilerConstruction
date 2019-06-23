package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.core.FuncDeclNode_Core;
import ast.declarations.FuncDeclNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_FuncCall;
import llvm.CodeSnippet_Type;
import writer.GeneratorSlave;

import java.util.ArrayList;
import java.util.List;

public class FuncCallNode extends AbstractSyntaxTree {
    private String m_FuncName;
    private List<ParameterNode> m_Params = new ArrayList<>();

    private FuncDeclNode m_FuncDecl;

    public FuncCallNode(String InFuncName) {
        this.m_FuncName = InFuncName;
    }

    public void AddParameter(AbstractSyntaxTree InParam) {
        InParam.SetParent(this);
        m_Params.add((ParameterNode) InParam);
    }


    public int GetParameterCount() {
        return m_Params.size();
    }

    public List<ParameterNode> GetParameterList() {
        return m_Params;
    }

    public ParameterNode GetParameter(int Index) {
        return m_Params.get(Index);
    }

    @Override
    public TypeNode CheckType() {
        // Function with Name exists?
        m_FuncDecl = GetOwningBlock().GetFunctionDeclaration(m_FuncName);
        if (m_FuncDecl == null) {
            throw new TypeCheckException(this, "Function with Name " + m_FuncName + " is not defined");
        }

        // Validate Types of used Parameters
        for (int i = 0; i < m_Params.size(); i++) {
            m_Params.get(i).CheckType();
        }

        // Given parameters fit function signature?
        m_FuncDecl.ValidateCall(this);

        return m_FuncDecl.GetType();
    }

    @Override
    public TypeNode GetType() {
        if (m_FuncDecl == null) {
            m_FuncDecl = GetOwningBlock().GetFunctionDeclaration(m_FuncName);
        }

        return m_FuncDecl.GetType();
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        // build function if not already created
        m_FuncDecl.CreateSnippet(slave, null);

        // Execute specialized function call
        if (m_FuncDecl instanceof FuncDeclNode_Core) {
            FuncDeclNode_Core coreFunc = (FuncDeclNode_Core) m_FuncDecl;
            if (coreFunc.HasCustomCallLogic()) {
                return coreFunc.CreateFunctionCall(slave, ctx, this);
            }
        } else {
            // TODO: default creation
            CodeSnippet_Type returnType = (CodeSnippet_Type) m_FuncDecl.GetType().CreateSnippet(slave, ctx);
            CodeSnippet_FuncCall call = new CodeSnippet_FuncCall(m_FuncName, returnType);

            for (ParameterNode param : m_Params) {
                call.AddParameter(param.CreateSnippet(slave, call));
            }

            return call;
        }

        return null;
    }
}
