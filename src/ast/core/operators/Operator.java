package ast.core.operators;

public enum Operator {
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

    AGN(".operatoragn");

    private String m_OperatorFunctionName;

    Operator(String operatorFunctionName) {
        m_OperatorFunctionName = operatorFunctionName;
    }

    public String GetOperatorFunctionName() {
        return m_OperatorFunctionName;
    }
}
