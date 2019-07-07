package writer.natives.math;

import writer.wrappers.ParamContainer;

public class NativeMathFunction_round extends NativeMathFunction {
    public NativeMathFunction_round(ParamContainer var) {
        super("llvm.round.f32", var);
    }
}
