package ast.core.functions.math;

import ast.core.FunctionNameWrapper;

public enum Math implements FunctionNameWrapper {
    MIN("min"),
    RANDOM("random"),
    SQR("sqr"),
    SQRT("sqrt"),
    ABS("abs"),
    SIN("sin"),
    COS("cos"),
    EXP("exp"),
    LN("ln"),
    TRUNC("trunc"),
    ROUND("round");

    private String m_FunctionName;

    Math(String functitionName) {
        m_FunctionName = functitionName;
    }

    public String GetFunctionName() {
        return m_FunctionName;
    }
}