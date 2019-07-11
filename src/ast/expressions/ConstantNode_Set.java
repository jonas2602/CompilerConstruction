package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.types.SetTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.ArrayList;
import java.util.List;


public class ConstantNode_Set extends ConstantNode {
    private ParamContainer m_GlobalParam;
    protected List<ConstantNode> m_Childs;

    public ConstantNode_Set(List<AbstractSyntaxTree> elements) {
        super("", new SetTypeNode((TypeNode) elements.get(0).GetType().Copy()));
        m_Childs = new ArrayList<>();

        for (AbstractSyntaxTree e : elements) {
            AddElement((ConstantNode) e);
        }
    }

    public void AddElement(ConstantNode node) {
        m_Childs.add(node);
        node.SetParent(this);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode setType = m_TypeNode.GetTypeDetails();
        for (ConstantNode child : m_Childs) {
            TypeNode childType = child.CheckType();
            if (!setType.CompareType(childType.GetCompareType())) {
                throw new TypeCheckException(this, "Not all set elements have the same type");
            }
        }

        return m_TypeNode;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_GlobalParam == null) {
            List<ParamContainer> paramList = new ArrayList<>();
            for (ConstantNode child : m_Childs) {
                paramList.add(child.CreateSnippet(slave));
            }

            m_GlobalParam = slave.CreateArrayConstant(paramList);
        }

        return m_GlobalParam;
    }
}
