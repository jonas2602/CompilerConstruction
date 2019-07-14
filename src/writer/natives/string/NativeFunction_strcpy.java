package writer.natives.string;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Other;
import writer.wrappers.TypeWrapper_Pointer;

public class NativeFunction_strcpy extends NativeFunction {
    public NativeFunction_strcpy(ParamContainer destination, ParamContainer source) {
        super("strcpy", TypeWrapper_Other.VOID);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Pointer.CHARPTR);

        m_CallParams.add(destination);
        m_CallParams.add(source);
    }

}
