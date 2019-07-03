package ast.expressions;

import ast.types.ArrayTypeNode;
import ast.types.NamedTypeNode;
import ast.types.PointerTypeNode;
import ast.types.PrimitiveTypeNode;
import llvm.CodeSnippet_Base;
import writer.ConstantWrapper;
import writer.GeneratorSlave;
import writer.ParamContainer;

public class ConstantNode_String extends ConstantNode {
    private ParamContainer m_GlobalParam;

    public ConstantNode_String(String data) {
        super(data, new ArrayTypeNode(data.length() + 1, PrimitiveTypeNode.CharNode));

        m_GlobalParam = null;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        if (m_GlobalParam == null) {
            m_GlobalParam = slave.CreateStringConstantNew(m_Data);
        }

        return m_GlobalParam;
        // return slave.CreateArrayElementPtr(strConst, new ConstantWrapper("0"));
    }
}
