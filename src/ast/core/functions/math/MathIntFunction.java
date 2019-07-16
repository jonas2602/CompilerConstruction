package ast.core.functions.math;

import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.natives.math.NativeMathFunction;
import writer.wrappers.ParamContainer;

public abstract class MathIntFunction extends MathFunction {

    public MathIntFunction(String name, Class<? extends NativeMathFunction> function) {
        super(name, PrimitiveTypeNode.IntNode, PrimitiveTypeNode.FloatNode, function);
    }

    public MathIntFunction(Math functionName, Class<? extends NativeMathFunction> function) {
        this(functionName.GetFunctionName(), function);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer result = super.CreateFunctionCall(slave, callNode);

        result = slave.CastFloatToInt(result);

        return result;
    }
}
