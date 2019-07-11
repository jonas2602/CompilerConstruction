package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.expressions.ConstantNode;

import java.lang.reflect.Type;

public class RangeTypeNode extends TypeNode {
    private AbstractSyntaxTree m_Min;
    private AbstractSyntaxTree m_Max;

    public RangeTypeNode(AbstractSyntaxTree min, AbstractSyntaxTree max) {
        this.m_Min = min;
        this.m_Max = max;
    }

    // TODO: How to check if value is in range?

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        // comparison can always be done with the type of the stored minNode,
        // because the minNode has the actual range type
        return GetCompareType().CompareType(otherTypeNode);
    }

    @Override
    public TypeNode CheckType() {
        TypeNode minType = m_Min.CheckType();
        TypeNode maxType = m_Max.CheckType();

        // TODO: Which types are allowed? int, real, char? more?

        // min and max must be of the same type
        if (!minType.CompareType(maxType)) {
            throw new TypeCheckException(this, "Type of min " + minType + " and max " + maxType + " are not equal");
        }

        // TODO: check that min is less than max
        if (GetRangeSize() < 0) {
            throw new TypeCheckException(this, "Min is not lower or equal to Max");
        }

        return GetType();
    }

    @Override
    public TypeNode GetType() {
        // TODO: is range a seperate type or should the mintype get returned
        return this;
    }

    @Override
    public TypeNode GetTypeDetails() {
        // TODO: use type of min?
        return this;
    }

    @Override
    public TypeNode GetCompareType() {
        return m_Min.GetType();
    }

    @Override
    public String toString() {
        return "Range(" + m_Min.GetType() + ")";
    }

    public int GetRangeSize() {
        if (!(m_Min instanceof ConstantNode) || !(m_Max instanceof ConstantNode)) {
            System.out.println("Range only supports int consts right now!");
            return 0;
        }
        int minValue = Integer.parseInt(((ConstantNode) m_Min).GetData());
        int maxValue = Integer.parseInt(((ConstantNode) m_Max).GetData());

        return (maxValue - minValue) + 1;
    }

    public boolean CheckInRange(int value) {
        int minValue = Integer.parseInt(((ConstantNode) m_Min).GetData());
        int maxValue = Integer.parseInt(((ConstantNode) m_Max).GetData());

        return minValue <= value && maxValue >= value;
    }
}
