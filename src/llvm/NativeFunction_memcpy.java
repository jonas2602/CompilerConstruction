package llvm;

import writer.ParamContainer;
import writer.TypeWrapper_Other;
import writer.TypeWrapper_Pointer;
import writer.TypeWrapper_Primitive;

import java.util.List;

public class NativeFunction_memcpy extends NativeFunction {
    public NativeFunction_memcpy(ParamContainer target, ParamContainer source, ParamContainer byteSize) {
        super("llvm.memcpy.p0i8.p0i8.i64", TypeWrapper_Other.VOID);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Primitive.LONG);
        AddParamDecl(TypeWrapper_Primitive.BOOL);

        m_CallParams.add(target);
        m_CallParams.add(source);
        m_CallParams.add(byteSize);
        m_CallParams.add(ParamContainer.BOOLCONTAINER(false));
    }

}
