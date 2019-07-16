package writer.natives.string;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;
import writer.wrappers.TypeWrapper_Primitive;

public class NativeFunction_strcmp extends NativeFunction {
    public NativeFunction_strcmp(ParamContainer str1, ParamContainer str2) {
        super("strcmp", TypeWrapper_Primitive.LONG);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Pointer.CHARPTR);

        m_CallParams.add(str1);
        m_CallParams.add(str2);
    }
}
