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
    public ConstantNode_String(String data) {

        super(data, new ArrayTypeNode(data.length() + 1, PrimitiveTypeNode.CharNode));
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        ParamContainer strConst = slave.CreateStringConstantNew(m_Data);
        return strConst;
        // return slave.CreateArrayElementPtr(strConst, new ConstantWrapper("0"));
    }
}
