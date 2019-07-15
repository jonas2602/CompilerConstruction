package ast.core;

import ast.BlockNode;
import ast.core.functions.casts.CastFunctions;
import ast.core.functions.io.*;
import ast.core.functions.list.FuncDeclNode_getLength;
import ast.core.functions.list.FuncDeclNode_setLength;
import ast.core.functions.math.MathFunctions;
import ast.core.functions.memory.*;
import ast.core.functions.set.*;
import ast.core.operators.*;
import ast.declarations.TypeDeclNode;
import ast.types.PrimitiveTypeNode;

public abstract class StdLib {
    public static BlockNode CreateStdLib() {
        BlockNode stdBlock = new BlockNode();
        // functions
        // io functions
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_writeln());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_readln());

        // memory functions
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_new());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_dispose());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_getmem());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_reallocmem());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_move());

        // math functions
        new MathFunctions().buildStd(stdBlock);

        //cast functions
        new CastFunctions().buildStd(stdBlock);

        // operators
        new BoolOperators().buildStd(stdBlock);
        new CharOperators().buildStd(stdBlock);
        new IntOperators().buildStd(stdBlock);
        new FloatOperators().buildStd(stdBlock);
        new PointerOperators().buildStd(stdBlock);
        new ArrayOperators().buildStd(stdBlock);
        new RecordOperators().buildStd(stdBlock);
        new PrimitiveOperators().buildStd(stdBlock);
        new StringOperators().buildStd(stdBlock);

        // list
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_getLength());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_setLength());

        // set
        new SetElementAppends().buildStd(stdBlock);
        new SetRangeAppends().buildStd(stdBlock);
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_inRange());
        stdBlock.AddFunctionDeclaration(new FuncDeclNode_min());
        new SetOperators().buildStd(stdBlock);

        // types
        stdBlock.AddTypeDeclaration(new TypeDeclNode("integer", PrimitiveTypeNode.IntNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("real", PrimitiveTypeNode.FloatNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("char", PrimitiveTypeNode.CharNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("boolean", PrimitiveTypeNode.BoolNode));

        return stdBlock;
    }
}
