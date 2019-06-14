package ast.declaration;

import ast.AbstractSyntaxTree;
import ast.BlockNode;
import ast.types.TypeNode;

import java.util.ArrayList;
import java.util.List;

public class ProcDeclNode extends AbstractSyntaxTree {
    private String m_Name;
    private List<ParamDeclNode> m_Params = new ArrayList<>();
    private BlockNode m_Block;

    public ProcDeclNode(String InName, BlockNode InBlock) {
        m_Name = InName;
        m_Block = InBlock;
        InBlock.SetParent(this);
    }

    public String GetName() {
        return m_Name;
    }

    public void AddParameter(ParamDeclNode InParam) {
        InParam.SetParent(this);
        m_Params.add(InParam);
    }

    public int GetParameterCount() {
        return m_Params.size();
    }

    public List<ParamDeclNode> GetParameterList() {
        return m_Params;
    }

    public ParamDeclNode GetParameter(int Index) {
        return m_Params.get(Index);
    }

    @Override
    public TypeNode CheckType() {
        // Block will also Check the Parameters, because they are stored in the block as variables
        m_Block.CheckType();
        return GetType();
    }
}