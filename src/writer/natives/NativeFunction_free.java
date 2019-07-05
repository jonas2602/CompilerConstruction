package writer.natives;

import writer.wrapper.*;

public class NativeFunction_free extends NativeFunction {
    public NativeFunction_free(ParamContainer target) {
        super("free", TypeWrapper_Other.VOID);

        AddParamDecl(TypeWrapper_Pointer.INTPTR);

        m_CallParams.add(target);
    }
}
