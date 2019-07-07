package ast.core.functions.math;

import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.natives.math.NativeMathFunction;
import writer.wrappers.ParamContainer;

public abstract class MathIntFunction extends MathFunction {

    public MathIntFunction(String InName, Class<? extends NativeMathFunction> function) {
        super(InName, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.FloatNode, function);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer result = super.CreateFunctionCall(slave, callNode);

        result = slave.CastFloatToInt(result);

        return result;
    }
}
