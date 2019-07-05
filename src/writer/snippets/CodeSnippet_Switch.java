package writer.snippets;

import writer.wrapper.ParamContainer;
import writer.wrapper.ValueWrapper_Variable;

import java.util.ArrayList;
import java.util.List;

public class CodeSnippet_Switch extends CodeSnippet_Base {
    private List<CodeSnippet_Base> m_Labels;
    private ParamContainer m_Comp;
    private ParamContainer m_Default;

    public CodeSnippet_Switch(ParamContainer comp, ParamContainer defJump) {
        m_Labels = new ArrayList<>();
        m_Comp = comp;
        m_Default = defJump;
    }

    public ValueWrapper_Variable AddParameter(CodeSnippet_Base param) {
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
        String head = String.format("switch %s, %s [ ", m_Comp.CreateParameterString(), m_Default.CreateParameterString());
        builder.append(head);

        for(CodeSnippet_Base c: m_Labels) {
            builder.append(c.Write());
        }

        builder.append("]");
        return builder.toString();
    }
}
