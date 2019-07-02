package llvm;

import writer.ParamContainer;
import writer.TypeWrapper_Other;
import writer.TypeWrapper_Pointer;
import writer.TypeWrapper_Primitive;

import java.util.List;

public class NativeFunction_printf extends NativeFunction {
    public NativeFunction_printf(ParamContainer InPlaceholderString, List<ParamContainer> InContent) {
        super("printf", TypeWrapper_Primitive.INT);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Other.VARIADIC);

        m_CallParams.add(InPlaceholderString);
        m_CallParams.addAll(InContent);
    }

}
