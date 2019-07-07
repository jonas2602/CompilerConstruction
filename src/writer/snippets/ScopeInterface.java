package writer.snippets;

import writer.wrappers.ValueWrapper_Variable;

public interface ScopeInterface {
    public abstract ValueWrapper_Variable AddParameter(CodeSnippet_Base param);
    public abstract ValueWrapper_Variable AddParameter(String param);

    // public void AddStatement(CodeSnippet_Base InStmt);
    // public void AddStatement(String InStmt);
    // public ValueWrapper_Variable AddStatementWithStorage(CodeSnippet_Base InStmt);
    // public ValueWrapper_Variable AddStatementWithStorage(String InStmt);
}
