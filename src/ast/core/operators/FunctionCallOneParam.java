package ast.core.operators;

import writer.GeneratorSlave;
import writer.ParamContainer;

public interface FunctionCallOneParam {
    public ParamContainer createFunctionCall(GeneratorSlave slave, ParamContainer lParam);
}
