package ast.core;

import ast.BlockNode;
import ast.core.operators.BoolOperators;
import ast.core.operators.CharOperators;
import ast.core.operators.FloatOperators;
import ast.core.operators.IntOperators;
import ast.declarations.TypeDeclNode;
import ast.types.PrimitiveTypeNode;

public abstract class StdLib {
    public static BlockNode CreateStdLib() {
        BlockNode stdBlock = new BlockNode();
        //functions
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_writeln());

        //operators
        new BoolOperators().buildStd(stdBlock);
        new CharOperators().buildStd(stdBlock);
        new IntOperators().buildStd(stdBlock);
        new FloatOperators().buildStd(stdBlock);

        //types
        stdBlock.AddTypeDeclaration(new TypeDeclNode("integer", PrimitiveTypeNode.IntNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("real", PrimitiveTypeNode.FloatNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("char", PrimitiveTypeNode.CharNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("boolean", PrimitiveTypeNode.BoolNode));

        return stdBlock;
    }
}
