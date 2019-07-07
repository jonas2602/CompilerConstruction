package writer.natives.math;

import writer.wrappers.ParamContainer;

public class NativeMathFunction_abs extends NativeMathFunction {
    public NativeMathFunction_abs(ParamContainer var) {
        super("llvm.fabs.f32", var);
    }
}
