package llvm;

import writer.*;

import java.util.ArrayList;
import java.util.List;

public abstract class NativeFunction {
    private String m_Name;
    private TypeWrapper m_ReturnType;
    private List<TypeWrapper> m_ParamTypes;
    private boolean m_bUseTypeExtension = false;

    protected List<ParamContainer> m_CallParams;

    protected NativeFunction() {
        m_ParamTypes = new ArrayList<>();
        m_CallParams = new ArrayList<>();
    }

    protected NativeFunction(String name, TypeWrapper returnType) {
        this();

        m_Name = name;
        m_ReturnType = returnType;
    }

    protected void AddParamDecl(TypeWrapper InType) {
        m_ParamTypes.add(InType);
        if (TypeWrapper_Other.VARIADIC.CompareType(InType)) {
            m_bUseTypeExtension = true;
        }
    }

    public void CreateFunctionDeclaration(GeneratorSlave slave) {
        slave.CreateFunctionDeclaration(m_Name, new CodeSnippet_Plain(m_ReturnType.CreateTypeName()), true);
        for (TypeWrapper paramType : m_ParamTypes) {
            slave.CreateFunctionParameter(paramType);
        }
        slave.ExitScope();
    }


    public ParamContainer CreateFunctionCall(GeneratorSlave slave) {
        ParamContainer outContainer = slave.CreateFunctionCall(m_Name, m_ReturnType, true);
        for (ParamContainer param : m_CallParams) {
            slave.CreateFunctionCallParameter(param);
        }

        if (m_bUseTypeExtension) {
            for (TypeWrapper paramType : m_ParamTypes) {
                slave.CreateFunctionCallExtension(paramType);
            }
        }

        return outContainer;
    }
}

