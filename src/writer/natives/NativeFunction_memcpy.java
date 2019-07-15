package writer.natives;

import writer.wrappers.*;

public class NativeFunction_memcpy extends NativeFunction {
    public NativeFunction_memcpy(ParamContainer target, ParamContainer source, int byteSize, int align) {
        this(target, source, ParamContainer.LONGCONTAINER(byteSize), align);
    }

    public NativeFunction_memcpy(ParamContainer target, ParamContainer source, ParamContainer byteSize, int align) {
        super("llvm.memcpy.p0i8.p0i8.i64", TypeWrapper_Other.VOID);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Primitive.LONG);
        AddParamDecl(TypeWrapper_Primitive.BOOL);

        // TODO: add alignment
        m_CallParams.add(target);
        m_CallParams.add(source);
        m_CallParams.add(byteSize);
        m_CallParams.add(ParamContainer.BOOLCONTAINER(false));
    }

}
