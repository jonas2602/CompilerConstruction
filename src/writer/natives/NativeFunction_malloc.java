package writer.natives;

import writer.wrappers.*;

public class NativeFunction_malloc extends NativeFunction {
    public NativeFunction_malloc(TypeWrapper type, int count) {
        this(type.GetTypeByteSize() * count);
    }

    public NativeFunction_malloc(int byteSize) {
        this(new ParamContainer(TypeWrapper_Primitive.LONG, new ValueWrapper_Constant(Integer.toString(byteSize))));
    }

    public NativeFunction_malloc(ParamContainer byteSizeParam) {
        super("malloc", TypeWrapper_Pointer.INTPTR);

        AddParamDecl(TypeWrapper_Primitive.LONG);

        m_CallParams.add(byteSizeParam);
    }
}
