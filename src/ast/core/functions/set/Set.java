package ast.core.functions.set;

import ast.core.FunctionNameWrapper;

public enum Set implements FunctionNameWrapper {
    INRANGE(".inrange"),
    APPEND(".append"),
    APPEND_RANGE(".appendRange");

    private String m_FunctionName;

    Set(String functitionName) {
        m_FunctionName = functitionName;
    }

    public String GetFunctionName() {
        return m_FunctionName;
    }
}