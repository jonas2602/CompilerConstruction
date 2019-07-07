package writer.natives.math;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Primitive;

public abstract class NativeMathFunction extends NativeFunction {

    public NativeMathFunction(String name, TypeWrapper_Primitive returnType, ParamContainer var) {
        super(name, returnType);

        AddParamDecl(TypeWrapper_Primitive.FLOAT);

        m_CallParams.add(var);
    }

    public NativeMathFunction(String name, ParamContainer var) {
        this(name, TypeWrapper_Primitive.FLOAT, var);
    }
}
