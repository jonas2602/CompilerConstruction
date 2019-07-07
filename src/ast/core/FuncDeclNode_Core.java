package ast.core;

import ast.BlockNode;
import ast.core.operators.Operator;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;

public abstract class FuncDeclNode_Core extends FuncDeclNode {
    protected boolean m_bCustomCallLogic = false;

    public FuncDeclNode_Core(String InName, TypeNode InReturnType) {
        super(InName, InReturnType, new BlockNode());
    }

    public FuncDeclNode_Core(Operator InOperator, TypeNode InReturnType) {
        this(InOperator.GetOperatorFunctionName(), InReturnType);
    }

    public boolean HasCustomCallLogic() {
        return m_bCustomCallLogic;
    }

    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        return null;
    }
}
