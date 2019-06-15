package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;

import java.lang.reflect.Type;

public class RangeTypeNode extends TypeNode {
    private AbstractSyntaxTree m_Min;
    private AbstractSyntaxTree m_Max;

    public RangeTypeNode(AbstractSyntaxTree InMin, AbstractSyntaxTree InMax) {
        this.m_Min = InMin;
        this.m_Max = InMax;
    }

    // TODO: How to check if value is in range?

    @Override
    public boolean CompareType(TypeNode OtherTypeNode) {
        // comparison can always be done with the type of the stored minNode,
        // because the minNode has the actual range type
        return GetCompareType().CompareType(OtherTypeNode);
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
}
