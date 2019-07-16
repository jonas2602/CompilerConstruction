package writer.snippets;

import writer.wrappers.ParamContainer;
import writer.wrappers.TypeWrapper;

import java.util.List;

public class CodeSnippetHelper {
    public static String MakeSeperatedString(String seperator, List<String> elements) {
        StringBuilder builder = new StringBuilder();

        if (elements.size() > 0) {
            builder.append(elements.get(0));
        }

        for (int i = 1; i < elements.size(); i++) {
            builder.append(seperator);
            builder.append(elements.get(i));
        }

        return builder.toString();
    }

    public static String MakeSeperatedSnippets(String seperator, List<CodeSnippet_Base> elements) {
        StringBuilder builder = new StringBuilder();

        if (elements.size() > 0) {
            builder.append(elements.get(0).Write());
        }

        for (int i = 1; i < elements.size(); i++) {
            builder.append(seperator);
            builder.append(elements.get(i).Write());
        }

        return builder.toString();
    }

    public static String MakeSeperatedParams(String seperator, List<ParamContainer> elements) {
        StringBuilder builder = new StringBuilder();

        if (elements.size() > 0) {
            builder.append(elements.get(0).CreateParameterString());
        }

        for (int i = 1; i < elements.size(); i++) {
            builder.append(seperator);
            builder.append(elements.get(i).CreateParameterString());
        }

        return builder.toString();
    }

    public static String MakeSeperatedTypes(String seperator, List<TypeWrapper> elements) {
        StringBuilder builder = new StringBuilder();

        if (elements.size() > 0) {
            builder.append(elements.get(0).CreateTypeName());
        }

        for (int i = 1; i < elements.size(); i++) {
            builder.append(seperator);
            builder.append(elements.get(i).CreateTypeName());
        }

        return builder.toString();
    }
}
