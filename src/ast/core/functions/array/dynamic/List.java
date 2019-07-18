package ast.core.functions.array.dynamic;

import ast.core.FunctionNameWrapper;

public enum List implements FunctionNameWrapper {
    GETLEN("length"),
    SETLEN("setlength");

    private String m_FunctionName;

    List(String functitionName) {
        m_FunctionName = functitionName;
    }

    public String GetFunctionName() {
        return m_FunctionName;
    }
}