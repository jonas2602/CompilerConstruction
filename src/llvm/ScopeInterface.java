package llvm;

import writer.VariableWrapper;

public interface ScopeInterface {
    public void AddStatement(CodeSnippet_Base InStmt);
    public void AddStatement(String InStmt);
    public VariableWrapper AddStatementWithStorage(CodeSnippet_Base InStmt);
    public VariableWrapper AddStatementWithStorage(String InStmt);
}
