package writer.natives;

import writer.wrapper.ParamContainer;
import writer.wrapper.TypeWrapper_Other;
import writer.wrapper.TypeWrapper_Pointer;
import writer.wrapper.TypeWrapper_Primitive;

import java.util.List;

public class NativeFunction_printf extends NativeFunction {
    public NativeFunction_printf(ParamContainer placeHolder, List<ParamContainer> content) {
        super("printf", TypeWrapper_Primitive.INT);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Other.VARIADIC);

        m_CallParams.add(placeHolder);
        m_CallParams.addAll(content);
    }

}
