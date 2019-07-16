package ast.core.functions.set;

import ast.BlockNode;
import ast.core.StdBuilder;

public class SetFunctions implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        new SetElementAppends().buildStd(std);
        new SetRangeAppends().buildStd(std);
        std.AddFunctionDeclaration(new FuncDeclNode_inRange());
    }
}
