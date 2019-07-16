package ast.core.functions.io;

import ast.core.FunctionNameWrapper;

public enum IO implements FunctionNameWrapper {
    READLN("readln"),
    WRITELN("writeln"),
    WRITE("write");

    private String m_FunctionName;

    IO(String functitionName) {
        m_FunctionName = functitionName;
    }

    public String GetFunctionName() {
        return m_FunctionName;
    }
}