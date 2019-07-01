package ast.core.operators;

public enum Operator {
    ADD("operator+"),
    SUB("operator-"),
    MUL("operator*"),
    DIV("operator/"),

    MOD("operator%"),
    INTDIV("operatorint/"),

    NEG("operator!"),

    AND("operator&&"),
    OR("operator||"),

    IN("operator#"),

    EQ("operator="),
    NE("operator<>"),
    LT("operator<"),
    LE("operator<="),
    GT("operator>"),
    GE("operator>="),

    INC("operator++"),
    DEC("operator--"),

    AGN("operator:");

    private String m_OperatorFunctionName;
    Operator(String operatorFunctionName) {
        m_OperatorFunctionName = operatorFunctionName;
    }

    public String GetOperatorFunctionName() {
        return m_OperatorFunctionName;
    }
}
