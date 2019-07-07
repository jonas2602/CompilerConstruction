package writer.natives.io;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Other;
import writer.wrappers.TypeWrapper_Pointer;
import writer.wrappers.TypeWrapper_Primitive;

import java.util.List;

public class NativeFunction_printf extends NativeFunction {
    public NativeFunction_printf(ParamContainer placeholder, List<ParamContainer> content) {
        super("printf", TypeWrapper_Primitive.INT);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Other.VARIADIC);

        m_CallParams.add(placeholder);
        m_CallParams.addAll(content);
    }

}
