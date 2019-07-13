package ast.core.operators;

import ast.BlockNode;
import ast.core.FuncDeclNode_Core;
import ast.core.StdBuilder;
import ast.declarations.ParamDeclNode;
import ast.types.*;
import writer.GeneratorSlave;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

public class CharOperators implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new AddChar());

        std.AddFunctionDeclaration(new XORChar());
        std.AddFunctionDeclaration(new ORChar());

        std.AddFunctionDeclaration(new AssignCharPointerArray());
    }

    public static class AssignCharPointerArray extends PascalType_Assignment {
        public AssignCharPointerArray() {
            super(Operator.AGN, new VoidTypeNode(), PointerTypeNode.CharPointerNode, ArrayTypeNode.CharArrayNode, (slave, lParam, rParam) -> {
                ParamContainer cast = slave.BitCast(rParam, TypeWrapper_Pointer.CHARPTR);
                slave.StoreInVariable(lParam, cast);
                return null;
            });
        }
    }

    public static class XORChar extends PascalType_Operator {
        public XORChar() {
            super(Operator.XOR, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, GeneratorSlave::XORIType);
        }
    }

    public static class ORChar extends PascalType_Operator {
        public ORChar() {
            super(Operator.OR, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, GeneratorSlave::ORIType);
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
