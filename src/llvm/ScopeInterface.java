package llvm;

import writer.VariableWrapper;

public interface ScopeInterface {
    public abstract VariableWrapper AddParameter(CodeSnippet_Base param);
    public abstract VariableWrapper AddParameter(String param);

    // public void AddStatement(CodeSnippet_Base InStmt);
    // public void AddStatement(String InStmt);
    // public VariableWrapper AddStatementWithStorage(CodeSnippet_Base InStmt);
    // public VariableWrapper AddStatementWithStorage(String InStmt);
}
