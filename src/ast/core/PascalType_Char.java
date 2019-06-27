package ast.core;

import ast.EPrimitiveType;
import ast.declarations.ParamDeclNode;
import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import llvm.CodeSnippet_Base;
import llvm.CodeSnippet_Plain;
import writer.GeneratorSlave;

public class PascalType_Char extends PascalType_Primitive {
    public PascalType_Char() {
        super(EPrimitiveType.CHAR, 8, false, "%c", "0");
    }


    public static class FuncDeclNode_addChar extends FuncDeclNode_Core {
        public FuncDeclNode_addChar() {
            super("operator+", PrimitiveTypeNode.CharNode);

            AddParameter(new ParamDeclNode("left", PrimitiveTypeNode.CharNode));
            AddParameter(new ParamDeclNode("right", PrimitiveTypeNode.CharNode));

            m_bCustomCallLogic = true;
            m_bInline = true;
        }

    }
}

