package ast.core.functions.math;

import ast.BlockNode;
import ast.core.StdBuilder;
import ast.core.functions.math.sqr.FuncDeclNode_fsqr;
import ast.core.functions.math.sqr.FuncDeclNode_isqr;
import ast.types.PrimitiveTypeNode;
import writer.natives.math.*;

public class MathFunctions implements StdBuilder {

    @Override
    public void buildStd(BlockNode std) {
        std.AddFunctionDeclaration(new FuncDeclNode_fsqr());
        std.AddFunctionDeclaration(new FuncDeclNode_isqr());

        std.AddFunctionDeclaration(new Sqrt());
        std.AddFunctionDeclaration(new IntSqrt());

        std.AddFunctionDeclaration(new Abs());
        std.AddFunctionDeclaration(new IntAbs());

        std.AddFunctionDeclaration(new Sin());
        std.AddFunctionDeclaration(new IntSin());

        std.AddFunctionDeclaration(new Cos());
        std.AddFunctionDeclaration(new IntCos());

        std.AddFunctionDeclaration(new Exp());
        std.AddFunctionDeclaration(new IntExp());

        std.AddFunctionDeclaration(new Ln());
        std.AddFunctionDeclaration(new IntLn());

        std.AddFunctionDeclaration(new FloatTrunc());

        std.AddFunctionDeclaration(new FloatRound());
    }

    public static class Sqrt extends MathFunction {
        public Sqrt() {
            super("sqrt", PrimitiveTypeNode.FloatNode, NativeMathFunction_sqrt.class);
        }
    }

    public static class IntSqrt extends MathFunction {
        public IntSqrt() {
            super("sqrt", PrimitiveTypeNode.IntNode, NativeMathFunction_sqrt.class);
        }
    }

    public static class Abs extends MathRecastFunction {
        public Abs() {
            super("abs", PrimitiveTypeNode.FloatNode, NativeMathFunction_abs.class);
        }
    }

    public static class IntAbs extends MathRecastFunction {
        public IntAbs() {
            super("abs", PrimitiveTypeNode.IntNode, NativeMathFunction_abs.class);
        }
    }

    public static class Sin extends MathFunction {
        public Sin() {
            super("sin", PrimitiveTypeNode.FloatNode, NativeMathFunction_sin.class);
        }
    }

    public static class IntSin extends MathFunction {
        public IntSin() {
            super("sin", PrimitiveTypeNode.IntNode, NativeMathFunction_sin.class);
        }
    }

    public static class Cos extends MathFunction {
        public Cos() {
            super("cos", PrimitiveTypeNode.FloatNode, NativeMathFunction_cos.class);
        }
    }

    public static class IntCos extends MathFunction {
        public IntCos() {
            super("cos", PrimitiveTypeNode.IntNode, NativeMathFunction_cos.class);
        }
    }

    public static class Exp extends MathFunction {
        public Exp() {
            super("exp", PrimitiveTypeNode.FloatNode, NativeMathFunction_exp.class);
        }
    }

    public static class IntExp extends MathFunction {
        public IntExp() {
            super("exp", PrimitiveTypeNode.IntNode, NativeMathFunction_exp.class);
        }
    }

    public static class Ln extends MathFunction {
        public Ln() {
            super("ln", PrimitiveTypeNode.FloatNode, NativeMathFunction_log.class);
        }
    }

    public static class IntLn extends MathFunction {
        public IntLn() {
            super("ln", PrimitiveTypeNode.IntNode, NativeMathFunction_log.class);
        }
    }

    public static class FloatTrunc extends MathIntFunction {
        public FloatTrunc() {
            super("trunc", NativeMathFunction_trunc.class);
        }
    }

    public static class FloatRound extends MathIntFunction {
        public FloatRound() {
            super("round", NativeMathFunction_round.class);
        }
    }
}
