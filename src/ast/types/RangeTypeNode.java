package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import ast.declarations.ConstDeclNode;
import ast.expressions.AccessNode_Variable;
import ast.expressions.ConstantNode;

import java.lang.reflect.Type;

public class RangeTypeNode extends TypeNode {
    private AbstractSyntaxTree m_Min;
    private AbstractSyntaxTree m_Max;

    public RangeTypeNode(int min, int max, TypeNode type) {
        this(new ConstantNode(Integer.toString(min), type), new ConstantNode(Integer.toString(max), type));
    }

    public RangeTypeNode(AbstractSyntaxTree min, AbstractSyntaxTree max) {
        this.m_Min = min;
        this.m_Max = max;

        min.SetParent(this);
        max.SetParent(this);
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

        if (m_Min instanceof AccessNode_Variable) {
            AccessNode_Variable av = (AccessNode_Variable) m_Min;
            if (!(av.GetDeclaration() instanceof ConstDeclNode)) {
                throw new RuntimeException("Typecheck failed at subrange because min is not constant!");
            }

            m_Min = ((ConstDeclNode) av.GetDeclaration()).GetConstant();
        }

        if (m_Max instanceof AccessNode_Variable) {
            AccessNode_Variable av = (AccessNode_Variable) m_Max;
            if (!(av.GetDeclaration() instanceof ConstDeclNode)) {
                throw new RuntimeException("Typecheck failed at subrange because max is not constant!");
            }

            m_Max = ((ConstDeclNode) av.GetDeclaration()).GetConstant();
        }

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

    public boolean CheckInRange(RangeTypeNode r) {
        int minValue = Integer.parseInt(((ConstantNode) r.m_Min).GetData());
        int maxValue = Integer.parseInt(((ConstantNode) r.m_Max).GetData());

        return CheckInRange(minValue, maxValue);
    }

    public boolean CheckInRange(int min, int max) {
        int minValue = Integer.parseInt(((ConstantNode) m_Min).GetData());
        int maxValue = Integer.parseInt(((ConstantNode) m_Max).GetData());

        //checks if min or max lies within this range
        return (minValue <= min && maxValue >= min) || (minValue <= max && maxValue >= max);
    }

    public AbstractSyntaxTree GetMin() {
        return m_Min;
    }

    public AbstractSyntaxTree GetMax() {
        return m_Max;
    }
}
