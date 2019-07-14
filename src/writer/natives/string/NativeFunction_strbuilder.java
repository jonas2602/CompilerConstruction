package writer.natives.string;

import writer.natives.NativeFunction;
import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper_Pointer;
import writer.wrappers.TypeWrapper_Primitive;

public class NativeFunction_strbuilder extends NativeFunction {
    public NativeFunction_strbuilder(ParamContainer left, ParamContainer right) {
        super("strbuilder", TypeWrapper_Pointer.CHARPTR);

        AddParamDecl(TypeWrapper_Primitive.CHAR);
        AddParamDecl(TypeWrapper_Primitive.CHAR);

        m_CallParams.add(left);
        m_CallParams.add(right);
    }

}
