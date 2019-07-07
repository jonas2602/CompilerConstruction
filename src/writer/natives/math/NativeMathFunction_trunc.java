package writer.natives.math;

import writer.wrappers.ParamContainer;

public class NativeMathFunction_trunc extends NativeMathFunction {
    public NativeMathFunction_trunc(ParamContainer var) {
        super("llvm.trunc.f32", var);
    }
}
