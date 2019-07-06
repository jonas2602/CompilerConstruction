package ast.core;

import ast.BlockNode;
import ast.core.functions.*;
import ast.core.operators.*;
import ast.declarations.TypeDeclNode;
import ast.types.PrimitiveTypeNode;

public abstract class StdLib {
    public static BlockNode CreateStdLib() {
        BlockNode stdBlock = new BlockNode();
        //functions
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_writeln());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_readln());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_new());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_dispose());

        //operators
        new BoolOperators().buildStd(stdBlock);
        new CharOperators().buildStd(stdBlock);
        new IntOperators().buildStd(stdBlock);
        new FloatOperators().buildStd(stdBlock);
        new PointerOperators().buildStd(stdBlock);
        //new GenericOperators().buildStd(stdBlock);

        //types
        stdBlock.AddTypeDeclaration(new TypeDeclNode("integer", PrimitiveTypeNode.IntNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("real", PrimitiveTypeNode.FloatNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("char", PrimitiveTypeNode.CharNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("boolean", PrimitiveTypeNode.BoolNode));

        return stdBlock;
    }
}
