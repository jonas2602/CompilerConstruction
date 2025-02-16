package writer.natives.memory;

import writer.wrappers.*;
import writer.natives.NativeFunction;

// Attention: realloc will reuse the existing memory if enough space for the new size!!!
//            -> new pointer can point to the same location but it's no deterministic behaviour
public class NativeFunction_realloc extends NativeFunction {
    public NativeFunction_realloc(ParamContainer source, int newSize) {
        this(source, new ParamContainer(TypeWrapper_Primitive.LONG, ValueWrapper_Constant.ValueWrapperInt(newSize)));
    }

    public NativeFunction_realloc(ParamContainer source, ParamContainer newSize) {
        super("realloc", TypeWrapper_Pointer.CHARPTR);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Primitive.LONG);

        m_CallParams.add(source);
        m_CallParams.add(newSize);
    }
}
