package llvm;

import writer.ParamContainer;
import writer.TypeWrapper;
import writer.VariableWrapper;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Switch extends CodeSnippet_Base {
    private List<CodeSnippet_Base> m_Labels;
    private CodeSnippet_Base m_Comp;
    private CodeSnippet_Base m_Default;

    public CodeSnippet_Switch(CodeSnippet_Base comp, CodeSnippet_Base defJump) {
        m_Labels = new ArrayList<>();
        m_Comp = comp;
        m_Default = defJump;
    }

    public VariableWrapper AddParameter(CodeSnippet_Base param) {
        m_Labels.add(param);
        return null;
    }

    @Override
    public List<String> WriteLines() {
        return new ArrayList<>() {{
            add(Write());
        }};
    }

    @Override
    public String Write() {
        StringBuilder builder = new StringBuilder();
        String head = String.format("switch %s, %s [ ", m_Comp.Write(), m_Default.Write());
        builder.append(head);

        for(CodeSnippet_Base c: m_Labels) {
            builder.append(c.Write());
        }

        builder.append("]");
        return builder.toString();
    }
}
