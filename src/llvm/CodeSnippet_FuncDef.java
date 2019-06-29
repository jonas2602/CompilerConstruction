package llvm;

import writer.VariableWrapper;

import java.util.List;
import java.util.ArrayList;

public class CodeSnippet_FuncDef extends CodeSnippet_FuncDecl implements ScopeInterface {
    private List<CodeSnippet_Base> m_Statements = new ArrayList<>();
    private int m_VariableCounter;
    // private List<VariableWrapper> m_IndexElements = new ArrayList<>();

    public CodeSnippet_FuncDef(String InName, CodeSnippet_Base InReturnType, int InVarIndexOffset) {
        super(InName, InReturnType);

        m_VariableCounter = InVarIndexOffset;
    }

    @Override
    public void AddStatement(CodeSnippet_Base InStmt) {
        m_Statements.add(InStmt);
    }

    @Override
    public void AddStatement(String InStmt) {
        m_Statements.add(new CodeSnippet_Plain(InStmt));
    }

    @Override
    public VariableWrapper AddStatementWithStorage(CodeSnippet_Base InStmt) {
        VariableWrapper var = VariableWrapper.SCOPEVAR(m_VariableCounter++);
        // String finalStmt = String.format("%%%d = %s", m_VariableCounter, InStmt.Write());
        m_Statements.add(new CodeSnippet_Args("%s = %s", var, InStmt));
        return var;
    }

    @Override
    public VariableWrapper AddStatementWithStorage(String InStmt) {
        return AddStatementWithStorage(new CodeSnippet_Plain(InStmt));
    }

    public VariableWrapper AddLabel() {
        m_Statements.add(new CodeSnippet_Plain(""));

        String finalStmt = String.format("; <label>:%d:", m_VariableCounter);
        m_Statements.add(new CodeSnippet_Plain(finalStmt));
        return VariableWrapper.SCOPEVAR(m_VariableCounter++);
    }

    @Override
    public List<String> WriteLines() {
        // for (int i = 0; i < m_IndexElements.size(); i++) {
        //     m_IndexElements.get(i).AssignScopeIndex(i);
        // }

        List<String> lines = new ArrayList<>();

        lines.add(String.format("define %s @%s(%s)", m_ReturnType.Write(), m_Name, MakeParameterString()) + " {");

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
