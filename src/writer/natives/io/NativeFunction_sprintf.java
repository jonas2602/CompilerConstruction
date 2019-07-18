package writer.natives.io;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Other;
import writer.wrappers.TypeWrapper_Pointer;
import writer.wrappers.TypeWrapper_Primitive;

import java.util.List;

public class NativeFunction_sprintf extends NativeFunction {
    public NativeFunction_sprintf(ParamContainer buffer, ParamContainer format, ParamContainer content) {
        super("sprintf", TypeWrapper_Primitive.INT);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Other.VARIADIC);

        m_CallParams.add(buffer);
        m_CallParams.add(format);
        m_CallParams.addAll(List.of(content));
    }

}
