package ast.core.operators;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.declarations.ParamDeclNode;
import ast.types.*;
import writer.ParamContainer;
import writer.TypeWrapper_Pointer;

public class CharOperators implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AddChar());

        std.AddFunctionDeclaration(new AssignCharPointerArray());
    }

    public static class AssignCharPointerArray extends PascalType_Assignment {
        public AssignCharPointerArray() {
            super(Operator.AGN, NamedTypeNode.VoidNode, PointerTypeNode.CharPointerNode, ArrayTypeNode.CharArrayNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.BitCast(rParam, TypeWrapper_Pointer.CHARPTR);
                slave.StoreInVariable(lParam, cast);
                return null;
            });
        }
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
