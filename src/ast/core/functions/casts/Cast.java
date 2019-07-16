package ast.core.functions.casts;

import ast.core.FunctionNameWrapper;

public enum Cast implements FunctionNameWrapper {
    CHR("chr"),
    ORD("ord"),
    BOOL("bool");

    private String m_FunctionName;

    Cast(String functitionName) {
        m_FunctionName = functitionName;
    }

    public String GetFunctionName() {
        return m_FunctionName;
    }
}
