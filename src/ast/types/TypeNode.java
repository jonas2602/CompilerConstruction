package ast.types;

import ast.AbstractSyntaxTree;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

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

    public String GetTypeName() {
        return "";
    }

    public void SetTypeName(String name) {

    }

    public String GetShortName() {
        return "";
    }

    @Override
    public TypeNode CheckType() {
        return GetType();
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

    public Set<WildcardTypeNode> GetWildcards() {
        return new HashSet<>();
    }

    public void InitVariable(GeneratorSlave slave, ParamContainer varParam) {

    }
}
