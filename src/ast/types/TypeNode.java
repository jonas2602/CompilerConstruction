package ast.types;

import ast.AbstractSyntaxTree;
import writer.ParamContainer;
import writer.TypeWrapper;

public abstract class TypeNode extends AbstractSyntaxTree {
    boolean m_bConstant;

    public TypeNode() {
        this(false);
    }

    public TypeNode(boolean bConstant) {
        m_bConstant = bConstant;
    }

    public String GetTypePlaceholder() {
        return "%s";
    }

    public boolean CompareType(TypeNode otherTypeNode) {
        return false;
    }

    public boolean IsConstant() {
        return m_bConstant;
    }

    public void SetTypeName(String name) {

    }

    @Override
    public TypeNode GetType() {
        return this;
    }

    // e.g. Type node is a named type and referes to a record type, the record definition would be returned
    public TypeNode GetTypeDetails() {
        return GetType();
    }

    public TypeNode GetCompareType() {
        return GetType();
    }

    public TypeWrapper GetWrappedType() {
        return null;
    }

    public ParamContainer GetDefaultValue() {
        return null;
    }
}
