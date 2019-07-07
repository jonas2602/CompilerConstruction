package ast.expressions;

import ast.types.ArrayTypeNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class ConstantNode_String extends ConstantNode {
    private ParamContainer m_GlobalParam;

    public ConstantNode_String(String data) {
        super(data, new ArrayTypeNode(data.length() + 1, PrimitiveTypeNode.ConstCharNode));

        m_GlobalParam = null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_GlobalParam == null) {
            m_GlobalParam = slave.CreateStringConstant(m_Data);
        }

        return m_GlobalParam;
        // return slave.CreateArrayElementPtr(strConst, new ValueWrapper_Constant("0"));
    }
}
