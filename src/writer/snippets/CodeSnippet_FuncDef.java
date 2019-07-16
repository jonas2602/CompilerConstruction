package writer.snippets;

import writer.wrappers.TypeWrapper;
import writer.wrappers.ValueWrapper_Variable;

import java.util.List;
import java.util.ArrayList;

public class CodeSnippet_FuncDef extends CodeSnippet_FuncDecl {
    private List<CodeSnippet_Base> m_Statements;
    private int m_VariableCounter;
    // private List<ValueWrapper_Variable> m_IndexElements = new ArrayList<>();

    public CodeSnippet_FuncDef(String name, TypeWrapper returnType, int varIndexOffset) {
        super(name, returnType);

        m_VariableCounter = varIndexOffset;
        m_Statements = new ArrayList<>();
    }

    // @Override
    public void AddStatement(CodeSnippet_Base stmt) {
        m_Statements.add(stmt);
    }

    // @Override
    public void AddStatement(String stmt) {
        m_Statements.add(new CodeSnippet_Plain(stmt));
    }

    // @Override
    public ValueWrapper_Variable AddStatementWithStorage(CodeSnippet_Base stmt) {
        ValueWrapper_Variable var = ValueWrapper_Variable.SCOPEVAR(m_VariableCounter++);
        // String finalStmt = String.format("%%%d = %s", m_VariableCounter, InStmt.Write());
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
        // for (int i = 0; i < m_IndexElements.size(); i++) {
        //     m_IndexElements.get(i).AssignScopeIndex(i);
        // }

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
//        String outString = "";
//
//        List<String> lines = WriteLines();
//        for (int i = 0; i < lines.size(); i++) {
//            if (i > 0) {
//                outString += '\n';
//            }
//            outString += lines.get(i);
//        }
//
//        return outString;
        return CodeSnippetHelper.MakeSeperatedString("\n", WriteLines());
    }
}
