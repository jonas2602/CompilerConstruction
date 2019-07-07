package writer.natives.math;

import writer.wrappers.ParamContainer;

public class NativeMathFunction_sin extends NativeMathFunction {
    public NativeMathFunction_sin(ParamContainer var) {
        super("llvm.sin.f32", var);
    }
}
