package ast.core.operators;

import writer.GeneratorSlave;
import writer.ParamContainer;

public interface FunctionCall {
    public ParamContainer createFunctionCall(GeneratorSlave slave, ParamContainer lParam, ParamContainer rParam);
}
