package writer.natives.string;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;
import writer.wrappers.TypeWrapper_Primitive;

// ATTENTION: copies source at the end of the target string
public class NativeFunction_strcat extends NativeFunction {
    public NativeFunction_strcat(ParamContainer destination, ParamContainer source) {
        super("strcat", TypeWrapper_Pointer.CHARPTR);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Pointer.CHARPTR);

        m_CallParams.add(destination);
        m_CallParams.add(source);
    }

}
