package ast.core.functions.memory;

import ast.BlockNode;
import ast.core.StdBuilder;

public class MemoryFunctions implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new FuncDeclNode_dispose());
        std.AddFunctionDeclaration(new FuncDeclNode_getmem());
        std.AddFunctionDeclaration(new FuncDeclNode_move());
        std.AddFunctionDeclaration(new FuncDeclNode_new());
        std.AddFunctionDeclaration(new FuncDeclNode_reallocmem());
    }
}
