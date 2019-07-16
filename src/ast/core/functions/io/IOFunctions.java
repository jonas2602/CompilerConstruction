package ast.core.functions.io;

import ast.BlockNode;
import ast.core.StdBuilder;

public class IOFunctions implements StdBuilder {
    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new FuncDeclNode_readln());
        std.AddFunctionDeclaration(new FuncDeclNode_writeln());
        std.AddFunctionDeclaration(new FuncDeclNode_write());
    }
}
