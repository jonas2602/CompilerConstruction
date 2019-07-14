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

        //comparison operators
        std.AddFunctionDeclaration(new EQChar());
        std.AddFunctionDeclaration(new NEChar());
        std.AddFunctionDeclaration(new LTChar());
        std.AddFunctionDeclaration(new LEChar());
        std.AddFunctionDeclaration(new GTChar());
        std.AddFunctionDeclaration(new GEChar());
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

    public static abstract class CharOperator extends PascalType_Operator {
        public CharOperator(Operator operator, TypeNode returnType, FunctionCallTwoParams operation) {
            super(operator, returnType, PrimitiveTypeNode.CharNode, PrimitiveTypeNode.CharNode, operation);
        }
    }

    public static class XorChar extends CharOperator {
        public XorChar() {
            super(Operator.XOR, PrimitiveTypeNode.CharNode, GeneratorSlave::XorIType);
        }
    }

    public static class OrChar extends CharOperator {
        public OrChar() {
            super(Operator.OR, PrimitiveTypeNode.CharNode, GeneratorSlave::OrIType);
        }
    }

    public static class AndChar extends CharOperator {
        public AndChar() {
            super(Operator.AND, PrimitiveTypeNode.CharNode, GeneratorSlave::AndIType);
        }
    }

    public static class EQChar extends CharOperator {
        public EQChar() {
            super(Operator.EQ, PrimitiveTypeNode.BoolNode, GeneratorSlave::EQIType);
        }
    }

    public static class NEChar extends CharOperator {
        public NEChar() {
            super(Operator.NE, PrimitiveTypeNode.BoolNode, GeneratorSlave::NEIType);
        }
    }

    public static class LTChar extends CharOperator {
        public LTChar() {
            super(Operator.LT, PrimitiveTypeNode.BoolNode, GeneratorSlave::LTIType);
        }
    }

    public static class LEChar extends CharOperator {
        public LEChar() {
            super(Operator.LE, PrimitiveTypeNode.BoolNode, GeneratorSlave::LEIType);
        }
    }

    public static class GTChar extends CharOperator {
        public GTChar() {
            super(Operator.GT, PrimitiveTypeNode.BoolNode, GeneratorSlave::GTIType);
        }
    }

    public static class GEChar extends CharOperator {
        public GEChar() {
            super(Operator.GE, PrimitiveTypeNode.BoolNode, GeneratorSlave::GEIType);
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
