package llvm;

import writer.VariableWrapper;

public interface ScopeInterface {
    public VariableWrapper AddParameter(CodeSnippet_Base InParam);
    public VariableWrapper AddParameter(String InParam);

    // public void AddStatement(CodeSnippet_Base InStmt);
    // public void AddStatement(String InStmt);
    // public VariableWrapper AddStatementWithStorage(CodeSnippet_Base InStmt);
    // public VariableWrapper AddStatementWithStorage(String InStmt);
}
