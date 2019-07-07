package ast.core.functions.math.sqr;

import ast.AbstractSyntaxTree;
import ast.core.FuncDeclNode_Core;
import ast.declarations.ParamDeclNode;
import ast.expressions.AccessInterface;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public class FuncDeclNode_isqr extends FuncDeclNode_Core {
    public FuncDeclNode_isqr() {
        super("sqr", PrimitiveTypeNode.IntNode);
        m_bCustomCallLogic = true;
        m_bInline = true;

        AddParameter(new ParamDeclNode("param", PrimitiveTypeNode.IntNode));
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        AbstractSyntaxTree param = callNode.GetParameter(0);
        ParamContainer container = param.CreateSnippet(slave);

        container = AccessInterface.TryLoadValue(slave, param, container);

        return slave.MulIntInt(container, container);
    }
}
