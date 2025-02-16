package ast.core.functions.math;

import ast.expressions.FuncCallNode;
import ast.types.PrimitiveTypeNode;
import writer.GeneratorSlave;
import writer.natives.math.NativeMathFunction;
import writer.wrappers.ParamContainer;

public abstract class MathRecastFunction extends MathFunction {

    public MathRecastFunction(String name, PrimitiveTypeNode type, Class<? extends NativeMathFunction> function) {
        super(name, type, type, function);
    }

    public MathRecastFunction(Math functionName,  PrimitiveTypeNode type, Class<? extends NativeMathFunction> function) {
        this(functionName.GetFunctionName(), type, function);
    }

    @Override
    public ParamContainer CreateFunctionCall(GeneratorSlave slave, FuncCallNode callNode) {
        ParamContainer result = super.CreateFunctionCall(slave, callNode);

        //recast
        if(m_Cast) {
            result = slave.CastFloatToInt(result);
        }

        return result;
    }
}
