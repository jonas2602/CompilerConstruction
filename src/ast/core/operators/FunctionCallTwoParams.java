package ast.core.operators;

import writer.GeneratorSlave;
import writer.wrapper.ParamContainer;

public interface FunctionCallTwoParams {
    public abstract ParamContainer createFunctionCall(GeneratorSlave slave, ParamContainer lParam, ParamContainer rParam);
}
