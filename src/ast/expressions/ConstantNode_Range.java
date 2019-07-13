package ast.expressions;

import ast.AbstractSyntaxTree;
import ast.types.TypeNode;

public class ConstantNode_Range extends ConstantNode {
    private AbstractSyntaxTree m_Min;
    private AbstractSyntaxTree m_Max;

    public ConstantNode_Range(int min, int max, TypeNode type) {
        this(new ConstantNode(Integer.toString(min), type), new ConstantNode(Integer.toString(max), type));
    }

    public ConstantNode_Range(AbstractSyntaxTree min, AbstractSyntaxTree max) {
        super("", min.GetType());

        this.m_Min = min;
        this.m_Max = max;

        min.SetParent(this);
        max.SetParent(this);
    }
}
