package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.TypeCheckException;
import ast.expressions.FuncCallNode;
import ast.types.NamedTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_FuncDef;
import llvm.CodeSnippet_Plain;
import llvm.CodeSnippet_Type;
import writer.GeneratorSlave;
import writer.ParamContainer;

import java.util.ArrayList;
import java.util.List;

public class FuncDeclNode extends AbstractSyntaxTree {
    protected String m_Name;
    protected List<ParamDeclNode> m_Params = new ArrayList<>();
    protected TypeNode m_ReturnType;
    protected BlockNode m_Block;

    protected boolean m_IsCreated = false;
    protected boolean m_bInline = false;

    public FuncDeclNode(String InName, TypeNode InReturnType, BlockNode InBlock) {
        m_Name = InName;
        m_ReturnType = InReturnType;
        m_Block = InBlock;

        m_ReturnType.SetParent(this);
        InBlock.SetParent(this);
    }

    public void AddParameter(ParamDeclNode InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);
    }

    public String GetName() {
        return m_Name;
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

    public boolean ValidateCall(FuncCallNode InCallNode) {
        // Parameter count fits the definition?
        if (InCallNode.GetParameterCount() != m_Params.size()) {
            // throw new TypeCheckException(InCallNode, "Function expected " + InCallNode.GetParameterCount() + " Arguments but received " + m_Params.size());
            return false;
        }

        // Compare given parameters to expected types
        for (int i = 0; i < m_Params.size(); i++) {
            TypeNode CallParamType = InCallNode.GetParameter(i).GetType();
            TypeNode FuncParamType = m_Params.get(i).GetType();
            if (!FuncParamType.CompareType(CallParamType)) {
                return false;
            }
        }

        return true;
    }

    public boolean CompareSignature(FuncDeclNode InOther) {
        // equal parameter counts?
        if (InOther.m_Params.size() != m_Params.size()) {
            return false;
        }

        // Compare parameter types
        for (int i = 0; i < m_Params.size(); i++) {
            TypeNode otherParamType = InOther.m_Params.get(i).GetType();
            TypeNode funcParamType = m_Params.get(i).GetType();
            if (!funcParamType.CompareType(otherParamType)) {
                return false;
            }
        }

        // Compare return types
        if (!m_ReturnType.CompareType(InOther.m_ReturnType)) {
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
        CodeSnippet_Base funcTypeSnippet = m_ReturnType.CreateSnippet(slave, ctx);
        CodeSnippet_FuncDef funcDef = slave.CreateFunctionDefinition(m_Name, funcTypeSnippet, m_Params.size(), true);

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