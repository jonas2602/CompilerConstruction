package ast;

public class ProgramNode extends AbstractSyntaxTree {
    private String m_Name;
    private AbstractSyntaxTree[] m_Params;
    private AbstractSyntaxTree m_Block;

    public ProgramNode(String InName, AbstractSyntaxTree[] InParams){
        this.m_Name = InName;
        this.m_Params = InParams;
    }

    public void SetBlock(AbstractSyntaxTree InBlock){
        this.m_Block = InBlock;
    }
}
