package ast;

public enum EPrimitiveType {
    INT("integer"),
    BOOL("boolean"),
    CHAR("char"),
    STRING("string"),
    FLOAT("real"),
    DOUBLE("real"),
    LONG("integer"),
    VOID("void");

    private final String label;

    EPrimitiveType(String label) {
        this.label = label;
    }

    public final String label() {
        return label;
    }
}
