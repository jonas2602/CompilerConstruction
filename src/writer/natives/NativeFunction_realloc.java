package writer.natives;

import writer.wrappers.*;

// Attention: realloc will reuse the existing memory if enough space for the new size!!!
//            -> new pointer can point to the same location but it's no deterministic behaviour
public class NativeFunction_realloc extends NativeFunction {
    public NativeFunction_realloc(ParamContainer source, int newSize) {
        this(source, new ParamContainer(TypeWrapper_Primitive.LONG, new ValueWrapper_Constant(newSize)));
    }

    public NativeFunction_realloc(ParamContainer source, ParamContainer newSize) {
        super("realloc", TypeWrapper_Pointer.CHARPTR);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Primitive.LONG);

        m_CallParams.add(source);
        m_CallParams.add(newSize);
    }
}
