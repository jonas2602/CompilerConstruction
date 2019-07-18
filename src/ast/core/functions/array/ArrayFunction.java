package ast.core.functions.array;

import ast.core.FunctionNameWrapper;

public enum ArrayFunction implements FunctionNameWrapper {
    LENGTH("length"),
    HIGH("high"),
    LOW("low");

    private String m_FunctionName;

    ArrayFunction(String functitionName) {
        m_FunctionName = functitionName;
    }

    public String GetFunctionName() {
        return m_FunctionName;
    }
}