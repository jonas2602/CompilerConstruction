package writer.natives;

import writer.GeneratorSlave;
import writer.TypeManager;
import writer.wrapper.*;

import java.util.List;

public class NativeFunction_scanf extends NativeFunction {
    public NativeFunction_scanf(ParamContainer format, ParamContainer outString) {
        super("__isoc99_scanf", TypeWrapper_Primitive.INT);

        AddParamDecl(TypeWrapper_Pointer.CHARPTR);
        AddParamDecl(TypeWrapper_Other.VARIADIC);

        m_CallParams.add(format);
        m_CallParams.add(outString);
    }
}
