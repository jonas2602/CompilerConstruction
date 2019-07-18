package writer.snippets;

import writer.wrappers.TypeWrapper;
import writer.wrappers.ValueWrapper_Variable;

import java.util.List;
import java.util.ArrayList;

public class CodeSnippet_FuncDef extends CodeSnippet_FuncDecl {
    private List<CodeSnippet_Base> m_Statements;
    private int m_VariableCounter;

    public CodeSnippet_FuncDef(String name, TypeWrapper returnType, int varIndexOffset) {
        super(name, returnType);

        m_VariableCounter = varIndexOffset;
        m_Statements = new ArrayList<>();
    }

    public void AddStatement(CodeSnippet_Base stmt) {
        m_Statements.add(stmt);
    }

    public void AddStatement(String stmt) {
        m_Statements.add(new CodeSnippet_Plain(stmt));
    }

    public ValueWrapper_Variable AddStatementWithStorage(CodeSnippet_Base stmt) {
        ValueWrapper_Variable var = ValueWrapper_Variable.SCOPEVAR(m_VariableCounter++);
        m_Statements.add(new CodeSnippet_Args("%s = %s", var, stmt));
        return var;
    }

    // @Override
    public ValueWrapper_Variable AddStatementWithStorage(String stmt) {
        return AddStatementWithStorage(new CodeSnippet_Plain(stmt));
    }

    public void AddStatementIncrementCounter(CodeSnippet_Base stmt) {
        AddStatement(stmt);
        m_VariableCounter++;
    }

    public ValueWrapper_Variable AddLabel() {
        m_Statements.add(new CodeSnippet_Plain(""));

        String finalStmt = String.format("; <label>:%d:", m_VariableCounter);
        m_Statements.add(new CodeSnippet_Plain(finalStmt));
        return ValueWrapper_Variable.SCOPEVAR(m_VariableCounter++);
    }

    @Override
    public List<String> WriteLines() {

        List<String> lines = new ArrayList<>();

        lines.add(String.format("define %s @%s(%s)", m_ReturnType.CreateTypeName(), m_Name, MakeParameterString()) + " {");

        for (CodeSnippet_Base stmt : m_Statements) {
            for (String line : stmt.WriteLines()) {
                lines.add("\t" + line);
            }
        }
        lines.add("}");

        return lines;
    }

    @Override
    public String Write() {
        return CodeSnippetHelper.MakeSeperatedString("\n", WriteLines());
    }
}
