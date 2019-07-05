package ast.expressions;

import ast.types.ArrayTypeNode;
import ast.types.NullTypeNode;
import ast.types.PrimitiveTypeNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

public class ConstantNode_NULL extends ConstantNode {
    public ConstantNode_NULL() {
        super("NIL", new NullTypeNode());
    }

    @Override
    public TypeNode GetType() {
        return m_TypeNode;
    }

    @Override
    public ParamContainer CreateSnippet(GeneratorSlave slave) {
        return ParamContainer.NULLCONTAINER();
    }
}
