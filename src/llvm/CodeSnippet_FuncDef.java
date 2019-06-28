package llvm;

import java.util.List;
import java.util.ArrayList;

public class CodeSnippet_FuncDef extends CodeSnippet_FuncDecl implements ScopeInterface {
    private List<CodeSnippet_Base> m_Statements = new ArrayList<>();
    private int m_VariableCounter;

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
    public int AddStatementWithStorage(CodeSnippet_Base InStmt) {
        String finalStmt = String.format("%%%d = %s", m_VariableCounter, InStmt.Write());
        m_Statements.add(new CodeSnippet_Plain(finalStmt));
        return m_VariableCounter++;
    }

    @Override
    public int AddStatementWithStorage(String InStmt) {
        String finalStmt = String.format("%%%d = %s", m_VariableCounter, InStmt);
        m_Statements.add(new CodeSnippet_Plain(finalStmt));
        return m_VariableCounter++;
    }

    public int AddLabel() {
        String labelStmt = String.format("; <label>:%d:", m_VariableCounter);
        m_Statements.add(new CodeSnippet_Plain(labelStmt));
        return m_VariableCounter++;
    }

    @Override
    public List<String> WriteLines() {
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
