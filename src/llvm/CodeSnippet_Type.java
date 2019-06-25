package llvm;

import ast.EPrimitiveType;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Type extends CodeSnippet_Base {
    public enum EType {
        VOID("void", "", ""),
        BOOL("i1", "%d", "1"),
        CHAR("i8", "%c", "0"),
        INT("i32", "%d", "0"),
        LONG("i64", "%ld", "0"),
        FLOAT("float", "%f", "0"),
        DOUBLE("double", "%f", "0"),
        STRING("i8*", "%s", "");

        private final String label;
        private final String placeholder;
        private final String defaultValue;

        EType(String label, String placeholder, String defaultValue) {
            this.label = label;
            this.placeholder = placeholder;
            this.defaultValue = defaultValue;
        }

        public static EType FromAstType(EPrimitiveType InType) {
            switch (InType){
                case INT: return EType.INT;
                case REAL: return EType.FLOAT;
                case CHAR: return EType.CHAR;
                case BOOL: return EType.BOOL;
                case STRING: return EType.STRING;
                default: return EType.VOID;
            }
        }

        public final String label() {
            return label;
        }
        public final String placeholder() {
            return placeholder;
        }
        public final String defaultValue() { return defaultValue; }
    }

    public static final CodeSnippet_Type SNIPPETTYPE_VOID = new CodeSnippet_Type(EType.VOID);
    public static final CodeSnippet_Type SNIPPETTYPE_BOOL = new CodeSnippet_Type(EType.BOOL);
    public static final CodeSnippet_Type SNIPPETTYPE_CHAR = new CodeSnippet_Type(EType.CHAR);
    public static final CodeSnippet_Type SNIPPETTYPE_INT = new CodeSnippet_Type(EType.INT);
    public static final CodeSnippet_Type SNIPPETTYPE_LONG = new CodeSnippet_Type(EType.LONG);
    public static final CodeSnippet_Type SNIPPETTYPE_FLOAT = new CodeSnippet_Type(EType.FLOAT);
    public static final CodeSnippet_Type SNIPPETTYPE_DOUBLE = new CodeSnippet_Type(EType.DOUBLE);

    private EType m_Type;

    public CodeSnippet_Type(EType InType) {
        m_Type = InType;
    }

    public EType GetType() {
        return m_Type;
    }

    @Override
    public List<String> WriteLines() {
        return new ArrayList<>() {{
            add(Write());
        }};
    }

    @Override
    public String Write() {
        return m_Type.label();
    }
}

