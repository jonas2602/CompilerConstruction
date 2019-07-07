package writer.natives.math;

import writer.wrappers.ParamContainer;

public class NativeMathFunction_cos extends NativeMathFunction {
    public NativeMathFunction_cos(ParamContainer var) {
        super("llvm.cos.f32", var);
    }
}
