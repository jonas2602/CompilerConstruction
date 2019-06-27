package ast.core;

import ast.BlockNode;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import writer.GeneratorSlave;
import writer.TypeContainer;

public abstract class FuncDeclNode_Core extends FuncDeclNode {
    protected boolean m_bCustomCallLogic = false;

    public FuncDeclNode_Core(String InName, TypeNode InReturnType) {
        super(InName, InReturnType, new BlockNode());
    }

    public boolean HasCustomCallLogic() {
        return m_bCustomCallLogic;
    }

    public TypeContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        return null;
    }
}
