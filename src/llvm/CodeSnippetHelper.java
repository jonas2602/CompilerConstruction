package llvm;

import java.util.List;

public class CodeSnippetHelper {
    static String MakeSeperatedString(String InSeperator, List<String> InElements) {
        String outString = "";

        for (int i = 0; i < InElements.size(); i++) {
            if (i > 0) {
                outString += InSeperator;
            }
            outString += InElements.get(i);
        }

        return outString;
    }

    static String MakeSeperatedSnippets(String InSeperator, List<CodeSnippet_Base> InElements) {
        String outString = "";

        for (int i = 0; i < InElements.size(); i++) {
            if (i > 0) {
                outString += InSeperator;
            }
            outString += InElements.get(i).Write();
        }

        return outString;
    }
}
