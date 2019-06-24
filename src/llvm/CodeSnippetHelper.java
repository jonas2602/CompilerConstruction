package llvm;

import java.util.List;

public class CodeSnippetHelper {
    public static String MakeSeperatedString(String InSeperator, List<String> InElements) {
        StringBuilder builder = new StringBuilder();

        if(InElements.size() > 0) {
            builder.append(InElements.get(0));
        }

        for (int i = 1; i < InElements.size(); i++) {
            builder.append(InSeperator);
            builder.append(InElements.get(i));
        }

        return builder.toString();
    }

    public static String MakeSeperatedSnippets(String InSeperator, List<CodeSnippet_Base> InElements) {
        StringBuilder builder = new StringBuilder();

        if(InElements.size() > 0) {
            builder.append(InElements.get(0).Write());
        }

        for (int i = 1; i < InElements.size(); i++) {
            builder.append(InSeperator);
            builder.append(InElements.get(i).Write());
        }

        return builder.toString();
    }
}
