package ast.types;

import writer.GeneratorSlave;
import writer.natives.NativeFunction_malloc;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

public class ArrayTypeNode_Dynamic extends RecordTypeNode {
    public static final ArrayTypeNode_Dynamic WildcardArrayNode = new ArrayTypeNode_Dynamic(new WildcardTypeNode());

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
    public TypeNode GetTypeDetails() {
        return m_ElementType;
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

    @Override
    public void InitVariable(GeneratorSlave slave, ParamContainer varParam) {
        // Init length
        ParamContainer lengthProp = slave.CreateArrayElementPtr(varParam, DynamicLengthIndex);
        m_EntryList.get(DynamicLengthIndex).InitVariable(slave, lengthProp);

        // allocate initial memory
        ParamContainer startProp = slave.CreateArrayElementPtr(varParam, DynamicStartIndex);
        ParamContainer startCharPtr = slave.CreateNativeCall(new NativeFunction_malloc(m_ElementType.GetWrappedType(), 0));
        ParamContainer startParam = slave.BitCast(startCharPtr, new TypeWrapper_Pointer(m_ElementType.GetWrappedType()));
        slave.StoreInVariable(startProp, startParam);
    }
}
