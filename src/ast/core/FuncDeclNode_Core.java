package ast.core;

import ast.BlockNode;
import ast.declarations.FuncDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.TypeNode;
import llvm.CodeSnippet_Base;
import writer.GeneratorSlave;

public class FuncDeclNode_Core extends FuncDeclNode {
    protected boolean m_CustomCallLogic = false;

    public FuncDeclNode_Core(String InName, TypeNode InReturnType) {
        super(InName, InReturnType, new BlockNode());
    }

    public boolean HasCustomCallLogic() {
        return m_CustomCallLogic;
    }

    public CodeSnippet_Base CreateFunctionCall(GeneratorSlave slave, CodeSnippet_Base ctx, FuncCallNode callNode) {
        return null;
    }
}
