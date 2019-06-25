package ast.core;

import ast.BlockNode;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import writer.GeneratorSlave;

public class FuncDeclNode_Core extends FuncDeclNode {
    protected boolean m_bCustomCallLogic = false;

    public FuncDeclNode_Core(String InName, TypeNode InReturnType) {
        super(InName, InReturnType, new BlockNode());
    }

    public boolean HasCustomCallLogic() {
        return m_bCustomCallLogic;
    }

    public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
        return null;
    }
}
