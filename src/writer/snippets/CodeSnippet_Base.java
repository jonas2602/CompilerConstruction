package writer.snippets;

import java.util.List;

public abstract class CodeSnippet_Base {
    public abstract List<String> WriteLines();
    public abstract String Write();

    @Override
    public String toString() {
        return Write();
    }
}
