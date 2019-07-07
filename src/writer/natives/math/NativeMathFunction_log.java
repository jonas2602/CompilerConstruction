package writer.natives.math;

import writer.wrappers.ParamContainer;

public class NativeMathFunction_log extends NativeMathFunction {
    public NativeMathFunction_log(ParamContainer var) {
        super("llvm.log.f32", var);
    }
}
