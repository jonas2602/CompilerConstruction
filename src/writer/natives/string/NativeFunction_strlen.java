package writer.natives.string;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;
import writer.wrappers.TypeWrapper_Primitive;

public class NativeFunction_strlen extends NativeFunction {
    public NativeFunction_strlen(ParamContainer str) {
        super("strlen", TypeWrapper_Primitive.LONG);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);

        m_CallParams.add(str);
    }

}
