package ast;

import ast.declaration.VarDeclNode;

import java.util.HashMap;

public class BlockNode extends AbstractSyntaxTree {
    AbstractSyntaxTree[] m_LabelDeclarations;
    AbstractSyntaxTree[] m_ConstantDeclarations;
    AbstractSyntaxTree[] m_TypeDeclarations;
    HashMap<String, VarDeclNode> m_VarDeclMap;
    AbstractSyntaxTree[] m_VariableDeclarations;
    AbstractSyntaxTree[] m_ProcedureDeclarations;
    AbstractSyntaxTree[] m_FunctionDeclarations;
    AbstractSyntaxTree m_CompoundStatement;

    public BlockNode(AbstractSyntaxTree[] InLabelDeclarations, AbstractSyntaxTree[] InConstantDeclarations, AbstractSyntaxTree[] InTypeDeclarations, AbstractSyntaxTree[] InVariableDeclarations, AbstractSyntaxTree[] InProcedureDeclarations, AbstractSyntaxTree[] InFunctionDeclarations, AbstractSyntaxTree InCompoundStatement) {
        m_LabelDeclarations = InLabelDeclarations;
        m_ConstantDeclarations = InConstantDeclarations;
        m_TypeDeclarations = InTypeDeclarations;
        m_VariableDeclarations = InVariableDeclarations;
        m_ProcedureDeclarations = InProcedureDeclarations;
        m_FunctionDeclarations = InFunctionDeclarations;
        m_CompoundStatement = InCompoundStatement;
    }

    void AddVariableDeclaration(VarDeclNode Variable) {
        if (m_VarDeclMap.containsKey(Variable.GetName())) {
            throw new RuntimeException("Variable with Name " + Variable.GetName() + " already defined in Scope");
        }

        m_VarDeclMap.put(Variable.GetName(), Variable);
    }

    @Override
    public BlockNode GetOwningBlock() {
        if (m_OwningBlock == null && m_Parent != null) {
            m_OwningBlock = m_Parent.GetOwningBlock();
        }

        return this;
    }

    public VarDeclNode GetVariableDeclaration(String Variable) {
        return
    }
}
