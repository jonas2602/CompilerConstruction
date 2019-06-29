package ast.core.operators;

import writer.GeneratorSlave;
import writer.ParamContainer;

public interface FunctionCallTwoParams {
    public ParamContainer createFunctionCall(GeneratorSlave slave, ParamContainer lParam, ParamContainer rParam);
}
