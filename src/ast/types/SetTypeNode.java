package ast.types;

import ast.AbstractSyntaxTree;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.Set;


public class SetTypeNode extends RecordTypeNode {
    public static final SetTypeNode WildcardSetNode() {
        return new SetTypeNode(new WildcardTypeNode());
    }

    public static String CreateDynamicSetName(TypeNode baseType) {
        return "set" + baseType.toString();
    }

    public enum ESetProps {
        LEFT,
        RIGHT,
        CONTENT
    }

    private TypeNode m_ElementType;
    // private List<AbstractSyntaxTree> m_Content;

    // private RecordTypeNode m_Struct;

    public SetTypeNode(TypeNode type) {
        m_ElementType = type;
        m_ElementType.SetParent(this);

        AddEntry(ESetProps.LEFT.name(), new PointerTypeNode(this));
        AddEntry(ESetProps.RIGHT.name(), new PointerTypeNode(this));
        AddEntry(ESetProps.CONTENT.name(), m_ElementType);
    }

//    public SetTypeNode(List<AbstractSyntaxTree> content) {
//        m_Content = content;
//
//        for(AbstractSyntaxTree a: m_Content) {
//            a.SetParent(this);
//        }
//    }

//    public void initStruct() {
//        m_Struct = new RecordTypeNode();
//        m_Struct.AddEntry("array", new ArrayTypeNode_Dynamic(m_ElementType));
//        m_Struct.AddEntry("counter", PrimitiveTypeNode.IntNode);
//        m_Struct.SetParent(this);
//        m_Struct.CheckType();
//    }

//    @Override
    public TypeNode CheckType() {
//        if (m_ElementType != null) {
            m_ElementType.CheckType();
//        } else {
//            if (m_Content.size() == 0) {
//                m_ElementType = PrimitiveTypeNode.WildcardPrimitiveNode();
//            } else {
//                m_ElementType = m_Content.get(0).CheckType();
//                for (AbstractSyntaxTree param : m_Content) {
//                    if (!m_ElementType.CompareType(param.CheckType())) {
//                        throw new TypeCheckException(this, "Types missmatch in set! " + m_ElementType.GetWrappedType().CreateTypeName() + " vs " + param.GetType().GetWrappedType().CreateTypeName());
//                    }
//                }
//            }
//        }
//
//        // ready to init struct now
//        initStruct();
        return GetType();
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_ElementType.GetType();
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof SetTypeNode)) {
            return false;
        }

        TypeNode otherElementType = ((SetTypeNode) otherTypeNode.GetCompareType()).m_ElementType;
        if (!m_ElementType.CompareType(otherElementType)) {
            return false;
        }

        return true;
    }

//    @Override
//    public ParamContainer CreateSnippet(GeneratorSlave slave) {
//        return m_Struct.CreateSnippet(slave);
//    }

    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        return m_ElementType.GetWildcards();
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new ArrayTypeNode_Dynamic((TypeNode) m_ElementType.Copy());
    }

}
