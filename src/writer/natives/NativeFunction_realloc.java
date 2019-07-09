package writer.natives;

import writer.wrappers.*;

public class NativeFunction_realloc extends NativeFunction {
    public NativeFunction_realloc(ParamContainer source, int newSize) {
        super("realloc", TypeWrapper_Pointer.CHARPTR);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Primitive.LONG);

        m_CallParams.add(source);
        m_CallParams.add(new ParamContainer(TypeWrapper_Primitive.LONG, new ValueWrapper_Constant(newSize)));
    }
}
