package writer.natives.math;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Primitive;

public class NativeFunction_sqrt extends NativeFunction {
    public NativeFunction_sqrt(ParamContainer var) {
        super("llvm.sqrt.f32", TypeWrapper_Primitive.FLOAT);

        AddParamDecl(TypeWrapper_Primitive.FLOAT);

        m_CallParams.add(var);
    }
}
