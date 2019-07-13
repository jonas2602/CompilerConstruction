package writer.natives;

import writer.wrappers.*;

public class NativeFunction_memset extends NativeFunction {
    public NativeFunction_memset(ParamContainer target, ParamContainer content, int byteSize) {
        super("llvm.memset.p0i8.i64", TypeWrapper_Other.VOID);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Primitive.CHAR);
        AddParamDecl(TypeWrapper_Primitive.LONG);
        AddParamDecl(TypeWrapper_Primitive.BOOL);

        // TODO: add alignment
        m_CallParams.add(target);
        m_CallParams.add(content);
        m_CallParams.add(new ParamContainer(TypeWrapper_Primitive.LONG, new ValueWrapper_Constant(byteSize)));
        m_CallParams.add(ParamContainer.BOOLCONTAINER(false));
    }

}
