package writer.natives.string;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;

// ATTENTION: allocates new memory on the heap
public class NativeFunction_strdup extends NativeFunction {
    public NativeFunction_strdup(ParamContainer str) {
        super("strdup", TypeWrapper_Pointer.CHARPTR);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);

        m_CallParams.add(str);
    }

}
