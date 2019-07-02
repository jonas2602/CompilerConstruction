package llvm;

public class CodeSnippet_TypeArray extends CodeSnippet_Type {
    private int m_Size;

    public CodeSnippet_TypeArray(EType type, int size) {
        super(type);

        m_Size = size;
    }

    @Override
    public String Write() {
        return String.format("[%d x %s]", m_Size, super.Write());
    }
}
