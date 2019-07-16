package ast.core.functions.memory;

import ast.core.FunctionNameWrapper;

public enum Memory implements FunctionNameWrapper {
    DISPOSE("dispose"),
    GETMEM("getmem"),
    MOVE("move"),
    NEW("new"),
    REALLOC("reallocmem");

    private String m_FunctionName;

    Memory(String functitionName) {
        m_FunctionName = functitionName;
    }

    public String GetFunctionName() {
        return m_FunctionName;
    }
}
