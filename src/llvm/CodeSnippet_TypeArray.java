package llvm;

public class CodeSnippet_TypeArray extends CodeSnippet_Type {
    private int m_Size;

    public CodeSnippet_TypeArray(EType InType, int InSize) {
        super(InType);

        m_Size = InSize;
    }

    @Override
    public String Write() {
        return String.format("[%d x %s]", m_Size, super.Write());
    }
}
