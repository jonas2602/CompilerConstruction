package ast.core.operators;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.declarations.ParamDeclNode;
import ast.types.PrimitiveTypeNode;

public class CharOperators implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AddChar());
    }

    //TODO: implement this (needs string support first)
    public static class AddChar extends FuncDeclNode_Core {
        public AddChar() {
            super("operator+", PrimitiveTypeNode.CharNode);

            AddParameter(new ParamDeclNode("left", PrimitiveTypeNode.CharNode));
            AddParameter(new ParamDeclNode("right", PrimitiveTypeNode.CharNode));

            m_bCustomCallLogic = true;
            m_bInline = true;
        }

    }
}
