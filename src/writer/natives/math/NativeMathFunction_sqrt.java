package writer.natives.math;

import writer.wrappers.ParamContainer;

public class NativeMathFunction_sqrt extends NativeMathFunction {
    public NativeMathFunction_sqrt(ParamContainer var) {
        super("llvm.sqrt.f32", var);
    }
}
