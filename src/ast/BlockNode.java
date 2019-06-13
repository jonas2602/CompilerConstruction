package ast;

public class BlockNode extends AbstractSyntaxTree {
    AbstractSyntaxTree[] m_LabelDeclarations;
    AbstractSyntaxTree[] m_ConstantDeclarations;
    AbstractSyntaxTree[] m_TypeDeclarations;
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
}
