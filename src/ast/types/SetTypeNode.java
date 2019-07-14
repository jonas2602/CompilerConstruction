package ast.types;

import ast.AbstractSyntaxTree;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.Set;


public class SetTypeNode extends ArrayTypeNode {
    public static final SetTypeNode IntSetNode = new SetTypeNode(PrimitiveTypeNode.IntNode);
    public static final SetTypeNode BoolSetNode = new SetTypeNode(PrimitiveTypeNode.IntNode);
    public static final SetTypeNode CharSetNode = new SetTypeNode(PrimitiveTypeNode.CharNode);

    public static final SetTypeNode WildcardSetNode() {
        return new SetTypeNode(new WildcardTypeNode());
    }

    public static String CreateDynamicSetName(TypeNode baseType) {
        return "set" + baseType.toString();
    }

    // not the type of the actual array
    // but the type the set is unsing in type checking
    private TypeNode m_SourceType;

    public SetTypeNode(TypeNode elementType) {
        super(256, PrimitiveTypeNode.CharNode);
        m_SourceType = elementType;
    }

    @Override
    public TypeNode CheckType() {
        // check used array
        super.CheckType();

        // check actual set type
        m_SourceType.CheckType();

        return GetType();
    }

    public TypeNode GetSetElementType() {
        return m_SourceType;
    }

    // @Override
    // public TypeNode GetTypeDetails() {
    //     return m_SourceType.GetType();
    // }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof SetTypeNode)) {
            return false;
        }

        TypeNode otherSourceType = ((SetTypeNode) otherTypeNode.GetCompareType()).m_SourceType;
        if (!m_SourceType.CompareType(otherSourceType)) {
            return false;
        }

        return true;
    }

    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        return m_SourceType.GetWildcards();
    }

    @Override
    public void InitVariable(GeneratorSlave slave, ParamContainer varParam) {
        slave.SetMemory(ParamContainer.CHARCONTAINER((char)0), varParam);
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new SetTypeNode((TypeNode) m_SourceType.Copy());
    }

}
