package llvm;

public interface ScopeInterface {
    public void AddStatement(CodeSnippet_Base InStmt);
    public int AddStatementWithStorage(String InStmt);
}
