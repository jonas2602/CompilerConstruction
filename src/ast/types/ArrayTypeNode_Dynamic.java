package ast.types;

import ast.AbstractSyntaxTree;
import writer.GeneratorSlave;
import writer.natives.NativeFunction_malloc;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

import java.util.Set;

public class ArrayTypeNode_Dynamic extends RecordTypeNode {
    public static final ArrayTypeNode_Dynamic WildcardArrayNode() {
        return new ArrayTypeNode_Dynamic(new WildcardTypeNode());
    }

    // screams for an enum
    public static final String DynamicLengthName = "length";
    public static final int DynamicLengthIndex = 0;
    public static final String DynamicStartName = "start";
    public static final int DynamicStartIndex = 1;

    public static String CreateDynamicArrayName(TypeNode baseType) {
        return "arr" + baseType.toString();
    }

    private TypeNode m_ElementType;

    public ArrayTypeNode_Dynamic(TypeNode elementType) {
        m_ElementType = elementType;
        AddEntry(DynamicLengthName, PrimitiveTypeNode.IntNode);
        AddEntry(DynamicStartName, new PointerTypeNode(m_ElementType));
    }

    @Override
    public String GetTypeName() {
        if (m_RecordName.isEmpty()) {
            return CreateDynamicArrayName(m_ElementType);
        }

        return m_RecordName;
    }

    @Override
    public TypeNode GetTypeDetails() {
        return m_ElementType.GetType();
    }

    @Override
    public boolean CompareType(TypeNode otherNodeType) {
        if (otherNodeType == null) {
            return false;
        }

        if (!(otherNodeType.GetCompareType() instanceof ArrayTypeNode_Dynamic)) {
            return false;
        }

        TypeNode otherElementType = ((ArrayTypeNode_Dynamic) otherNodeType.GetCompareType()).m_ElementType;
        if (!m_ElementType.CompareType(otherElementType)) {
            return false;
        }

        return true;
    }

    // @Override
    // public void InitVariable(GeneratorSlave slave, ParamContainer varParam) {
    //     // Init length
    //     ParamContainer lengthProp = slave.CreateArrayElementPtr(varParam, DynamicLengthIndex);
    //     m_EntryList.get(DynamicLengthIndex).InitVariable(slave, lengthProp);
    //
    //     // allocate initial memory
    //     ParamContainer startPropPtr = slave.CreateArrayElementPtr(varParam, DynamicStartIndex);
    //     if (m_ElementType.GetCompareType() instanceof ArrayTypeNode_Dynamic) {
    //         // usually pointer would not get initialized -> need to load ptr element to initialize it
    //         ParamContainer startProp = slave.LoadFromVariable(startPropPtr);
    //         m_ElementType.InitVariable(slave, startProp);
    //     } else {
    //         ParamContainer startCharPtr = slave.CreateNativeCall(new NativeFunction_malloc(m_ElementType.GetWrappedType(), 0));
    //         ParamContainer startParam = slave.BitCast(startCharPtr, new TypeWrapper_Pointer(m_ElementType.GetWrappedType()));
    //         slave.StoreInVariable(startPropPtr, startParam);
    //     }
    // }


    @Override
    public Set<WildcardTypeNode> GetWildcards() {
        return m_ElementType.GetWildcards();
    }

    @Override
    public AbstractSyntaxTree Copy() {
        return new ArrayTypeNode_Dynamic((TypeNode) m_ElementType.Copy());
    }
}
