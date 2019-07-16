package ast.core.functions.list;

import ast.BlockNode;
import ast.core.StdBuilder;

public class ListFunctions implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new FuncDeclNode_getLength());
        std.AddFunctionDeclaration(new FuncDeclNode_setLength());
    }
}
