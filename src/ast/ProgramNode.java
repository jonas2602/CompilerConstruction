package ast;

import ast.types.TypeNode;

import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends AbstractSyntaxTree {
    private String m_Name;
    private List<AbstractSyntaxTree> m_Params = new ArrayList<>();
    private BlockNode m_Block;

    public ProgramNode(String InName) {
        this.m_Name = InName;
    }

    public void AddParameter(AbstractSyntaxTree InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);
    }

    public void SetBlock(BlockNode InBlock) {
        InBlock.SetParent(this);
        this.m_Block = InBlock;
    }

    @Override
    public TypeNode CheckType() {
        // the block will take care of checking the parameter types

        m_Block.CheckType();
        return null;
    }
}
