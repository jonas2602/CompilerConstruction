package ast.types;

import ast.AbstractSyntaxTree;
import ast.TypeCheckException;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

import java.util.List;

public class SetTypeNode extends TypeNode {
    private TypeNode m_ElementType;
    private List<AbstractSyntaxTree> m_Content;

    private RecordTypeNode m_Struct;

    public SetTypeNode(TypeNode type) {
        m_ElementType = type;
        m_ElementType.SetParent(this);
    }

    public SetTypeNode(List<AbstractSyntaxTree> content) {
        m_Content = content;

        for(AbstractSyntaxTree a: m_Content) {
            a.SetParent(this);
        }
    }

    public void initStruct() {
        m_Struct = new RecordTypeNode();
        m_Struct.AddEntry("array", new ArrayTypeNode_Dynamic(m_ElementType));
        m_Struct.AddEntry("counter", PrimitiveTypeNode.IntNode);
        m_Struct.SetParent(this);
        m_Struct.CheckType();
    }

    @Override
    public TypeNode CheckType() {
        if (m_ElementType != null) {
            m_ElementType.CheckType();
        }
        else {
           if(m_Content.size() == 0) {
               m_ElementType = PrimitiveTypeNode.WildcardPrimitiveNode();
           }
           else {
               m_ElementType = m_Content.get(0).CheckType();
               for(AbstractSyntaxTree param: m_Content) {
                   if(!m_ElementType.CompareType(param.CheckType())) {
                       throw new TypeCheckException(this, "Types missmatch in set! "+m_ElementType.GetWrappedType().CreateTypeName()+" vs "+param.GetType().GetWrappedType().CreateTypeName());
                   }
               }
           }
        }

        //ready to init struct now
        initStruct();
        return super.CheckType();
    }

    @Override
    public boolean CompareType(TypeNode otherTypeNode) {
        if (otherTypeNode == null) {
            return false;
        }

        if (!(otherTypeNode.GetCompareType() instanceof SetTypeNode)) {
            return false;
        }

        TypeNode OtherType = otherTypeNode.GetTypeDetails();
        if (OtherType != m_ElementType) {
            return false;
        }

        return true;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return m_Struct.CreateSnippet(slave);
    }
}
