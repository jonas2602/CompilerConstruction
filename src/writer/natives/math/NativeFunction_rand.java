package writer.natives.math;

import writer.natives.NativeFunction;
import writer.wrappers.TypeWrapper_Primitive;

public class NativeFunction_rand extends NativeFunction {
    public NativeFunction_rand() {
        super("rand", TypeWrapper_Primitive.INT);
    }
}
