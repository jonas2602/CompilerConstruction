package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.TypeCheckException;
import ast.expressions.ConstantNode;
import ast.expressions.FuncCallNode;
import ast.types.NamedTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_FuncDef;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;
import writer.ParamContainer;
import writer.TypeWrapper;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode extends AbstractSyntaxTree {
    protected String m_Name;
    protected List<ParamDeclNode> m_Params;
    protected TypeNode m_ReturnType;
    protected BlockNode m_Block;

    protected boolean m_IsCreated = false;
    protected boolean m_bInline = false;

    public FuncDeclNode(String name, TypeNode returnType, BlockNode block) {
        m_Name = name;
        m_ReturnType = returnType;
        m_Block = block;

        m_ReturnType.SetParent(this);
        m_Block.SetParent(this);

        m_Params = new ArrayList<>();
    }

    public void AddParameter(ParamDeclNode InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);
    }

    public String GetName() {
        return m_Name;
    }

    public List<ParamDeclNode> GetParameters() {
        return m_Params;
    }

    public ParamDeclNode GetParameter(int index) {
        return m_Params.get(index);
    }

    public boolean IsInline() {
        return m_bInline;
    }

    public boolean IsVoid() {
        return m_ReturnType.CompareType(NamedTypeNode.VoidNode);
    }

    @Override
    public TypeNode GetType() {
        return m_ReturnType;
    }

    // No need the Check Child types, because block is handled by parent class,
    // checking the block includes the return type because its stored in the block
    // as variable

    @Override
    public TypeNode CheckType() {
        // Block will also Check the Parameters, because they are stored in the block as variables
        m_Block.CheckType();
        return GetType();
    }

    public boolean ValidateCall(FuncCallNode callNode) {
        // Parameter count fits the definition?
        if (callNode.GetParameterCount() != m_Params.size()) {
            // throw new TypeCheckException(callNode, "Function expected " + callNode.GetParameterCount() + " Arguments but received " + m_Params.size());
            return false;
        }

        // Compare given parameters to expected types
        for (int i = 0; i < m_Params.size(); i++) {
            TypeNode CallParamType = callNode.GetParameter(i).GetType();
            TypeNode FuncParamType = m_Params.get(i).GetType();

            // "VAR" parameters only accept variables, no constants
            if (callNode.GetParameter(i) instanceof ConstantNode && m_Params.get(i).IsByReference()) {
                return false;
            }

            if (!FuncParamType.CompareType(CallParamType)) {
                return false;
            }
        }

        return true;
    }

    public boolean CompareSignature(FuncDeclNode other) {
        // equal parameter counts?
        if (other.m_Params.size() != m_Params.size()) {
            return false;
        }

        // Compare parameter types
        for (int i = 0; i < m_Params.size(); i++) {
            TypeNode otherParamType = other.m_Params.get(i).GetType();
            TypeNode funcParamType = m_Params.get(i).GetType();
            if (!funcParamType.CompareType(otherParamType)) {
                return false;
            }
        }

        // Compare return types
        if (!m_ReturnType.CompareType(other.m_ReturnType)) {
            return false;
        }

        return true;
    }

    @Override
    public CodeSnippet_Base CreateSnippet(GeneratorSlave slave, CodeSnippet_Base ctx) {
        // Only build once
        if (m_IsCreated) return null;
        m_IsCreated = true;

        // Create Function Header
        TypeWrapper funcType = m_ReturnType.GetWrappedType();
        CodeSnippet_FuncDef funcDef = slave.CreateFunctionDefinition(m_Name, funcType, m_Params.size(), true);

        // Add Parameters
        for (ParamDeclNode param : m_Params) {
            param.CreateSnippet(slave);
            // CodeSnippet_Base paramSnippet = param.CreateSnippet(slave, funcDef);
            // int ScopeIndex = funcDef.AddParameter(paramSnippet);
            // param.SetScopeIndex(ScopeIndex);
        }
        // Create Function Body
        m_Block.CreateSnippet(slave, funcDef);

        // Function should have return type?
        if (m_ReturnType.CompareType(NamedTypeNode.VoidNode)) {
            funcDef.AddStatement(new CodeSnippet_Plain("ret void"));
        } else {
            // TODO: Non Primitive Types
            VarDeclNode varDecl = m_Block.GetVariableDeclaration(m_Name);
            ParamContainer outParam = varDecl.CreateSnippet(slave);
            outParam = slave.LoadFromVariable(outParam);
            slave.CreateReturnStmt(outParam);
            // String OutValue = varDecl.HasScopeIndex() ? "%" + varDecl.GetScopeIndex() : ((PrimitiveTypeNode) varDecl.GetType()).GetTypeDefault();
            // funcDef.AddStatement(slave.CreateReturnStmt(funcTypeSnippet, OutValue));
        }

        // Remove Function from stack
        slave.ExitScope();

        return funcDef;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}