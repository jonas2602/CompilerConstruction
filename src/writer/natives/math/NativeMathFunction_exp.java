package writer.natives.math;

import writer.wrappers.ParamContainer;

public class NativeMathFunction_exp extends NativeMathFunction {
    public NativeMathFunction_exp(ParamContainer var) {
        super("llvm.exp.f32", var);
    }
}
