package ast.core.operators;

import ast.core.FunctionNameWrapper;

public enum Operator implements FunctionNameWrapper {
    ADD(".operatoradd"),
    SUB(".operatorsub"),
    MUL(".operatormul"),
    DIV(".operatordiv"),

    MOD(".operatormod"),
    INTDIV(".operatorintdiv"),

    NEG(".operatorneg"),

    AND(".operatorand"),
    OR(".operatoror"),
    XOR( ".operatorxor"),
    INV(".operatorinv"),

    IN(".operatorin"),

    EQ(".operatoreq"),
    NE(".operatorne"),
    LT(".operatorlt"),
    LE( ".operatorle"),
    GT(".operatorgt"),
    GE(".operatorge"),

    INC("inc"),
    DEC("dec"),

    AGN(".operatoragn"),

    HIGH("high"),
    LOW("low"),

    LENGTH("length");

    private String m_OperatorFunctionName;

    Operator(String operatorFunctionName) {
        m_OperatorFunctionName = operatorFunctionName;
    }

    public String GetFunctionName() {
        return m_OperatorFunctionName;
    }
}
