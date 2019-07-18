package ast.core;

import ast.BlockNode;
import ast.core.functions.array.fixed.FixedArrayFunctions;
import ast.core.functions.casts.CastFunctions;
import ast.core.functions.io.*;
import ast.core.functions.array.dynamic.ListFunctions;
import ast.core.functions.math.MathFunctions;
import ast.core.functions.memory.*;
import ast.core.functions.set.*;
import ast.core.functions.string.StringFunctions;
import ast.core.operators.*;
import ast.declarations.TypeDeclNode;
import ast.types.PrimitiveTypeNode;

public abstract class StdLib {
    public static BlockNode CreateStdLib() {
        BlockNode stdBlock = new BlockNode();
        // functions
        // io functions
        new IOFunctions().buildStd(stdBlock);

        // memory functions
        new MemoryFunctions().buildStd(stdBlock);

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
        new SetOperators().buildStd(stdBlock);

        // dynamic
        new ListFunctions().buildStd(stdBlock);
        new FixedArrayFunctions().buildStd(stdBlock);

        // set
        new SetFunctions().buildStd(stdBlock);

        //strings
        new StringFunctions().buildStd(stdBlock);

        // types
        stdBlock.AddTypeDeclaration(new TypeDeclNode("integer", PrimitiveTypeNode.IntNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("real", PrimitiveTypeNode.FloatNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("char", PrimitiveTypeNode.CharNode));
        stdBlock.AddTypeDeclaration(new TypeDeclNode("boolean", PrimitiveTypeNode.BoolNode));

        return stdBlock;
    }
}
