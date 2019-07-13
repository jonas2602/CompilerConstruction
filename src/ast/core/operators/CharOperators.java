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

        std.AddFunctionDeclaration(new XorChar());
        std.AddFunctionDeclaration(new OrChar());
        std.AddFunctionDeclaration(new AndChar());
        std.AddFunctionDeclaration(new NegChar());
        std.AddFunctionDeclaration(new InvChar());

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

    public static class XorChar extends PascalType_Operator {
        public XorChar() {
            super(Operator.XOR, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, GeneratorSlave::XorIType);
        }
    }

    public static class OrChar extends PascalType_Operator {
        public OrChar() {
            super(Operator.OR, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, GeneratorSlave::OrIType);
        }
    }

    public static class AndChar extends PascalType_Operator {
        public AndChar() {
            super(Operator.AND, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, GeneratorSlave::AndIType);
        }
    }

    public static class NegChar extends PascalType_SingleOperator {
        public NegChar() {
            super(Operator.NEG, PrimitiveTypeNode.CharNode, GeneratorSlave::NegateInt);
        }
    }

    public static class InvChar extends PascalType_SingleOperator {
        public InvChar() {
            super(Operator.INV, PrimitiveTypeNode.CharNode, GeneratorSlave::InvIType);
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
