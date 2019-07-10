package ast.declarations;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.expressions.*;
import ast.types.TypeNode;
import ast.types.VoidTypeNode;
import writer.snippets.CodeSnippet_Base;
import writer.snippets.CodeSnippet_FuncDef;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

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

        // add return type as block variable
        if (!(returnType instanceof VoidTypeNode)) {
            m_Block.AddParameterDeclaration(new VarDeclNode(name, returnType));
        }
    }

    public void AddParameter(ParamDeclNode InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);

        // add parameter to block for accessability as variable
        m_Block.AddParameterDeclaration(InParam);
    }

    public void AddParameter(String name, TypeNode type) {
        AddParameter(name, type, false);
    }

    public void AddParameter(String name, TypeNode type, boolean bReference) {
        AddParameter(new ParamDeclNode(name, type, bReference));
    }

    public String GetName() {
        return m_Name;
    }

    public void SetName(String name) {
        m_Name = name;
    }

    public List<ParamDeclNode> GetParameters() {
        return m_Params;
    }

    public ParamDeclNode GetParameter(int index) {
        if (index < m_Params.size()) {
            return m_Params.get(index);
        }
        return m_Block.GetPassDownParam(index - m_Params.size());
    }

    public boolean IsInline() {
        return m_bInline;
    }

    public boolean IsVoid() {
        return m_ReturnType.CompareType(new VoidTypeNode());
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

    public FuncDeclNode ValidateCall(FuncCallNode callNode) {
        int size = m_Params.size();
        if (callNode.GetParameterCount() != size) {
            size += m_Block.GetNumberPassDownParams();
            if (callNode.GetParameterCount() != size) {
                return null;
            }
        }

        // Compare given parameters to expected types
        for (int i = 0; i < size; i++) {
            TypeNode callParamType = callNode.GetParameter(i).GetType();
            ParamDeclNode paramDecl = GetParameter(i);

            if (paramDecl.IsByReference()) {
                AbstractSyntaxTree param = callNode.GetParameter(i);

                // "VAR" parameters only accept variables, no constants
                if (param instanceof ConstantNode) {
                    return null;
                }

                // don't accept const variables if passed by reference
                if (((AccessInterface) param).GetVarDeclNode() instanceof ConstDeclNode) {
                    return null;
                }
            }

            if (!paramDecl.GetType().CompareType(callParamType)) {
                return null;
            }
        }

        if (size == m_Params.size()) {
            for (VarDeclNode param : m_Block.GetPassDownParams()) {
                AccessNode_Variable var = new AccessNode_Variable(param.GetName());
                callNode.AddParameter(var);
                var.GetType();
            }
        }

        return this;
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
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        // Only build once
        if (m_IsCreated) return null;
        m_IsCreated = true;

        // Create Function Header
        TypeWrapper funcType = m_ReturnType.GetWrappedType();
        CodeSnippet_FuncDef funcDef = slave.CreateFunctionDefinition(m_Name, funcType, m_Params.size() + m_Block.GetNumberPassDownParams(), true);

        // Add Parameters
        for (ParamDeclNode param : m_Params) {
            param.CreateSnippet(slave);
        }

        for (ParamDeclNode param : m_Block.GetPassDownParams()) {
            param.CreateSnippet(slave);
        }

        // Create Function Body
        m_Block.CreateSnippet(slave, funcDef);

        // Function should have return type?
        if (m_ReturnType.CompareType(new VoidTypeNode())) {
            slave.CreateReturnStmt(ParamContainer.VOIDCONTAINER());
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

        return null;
    }
}