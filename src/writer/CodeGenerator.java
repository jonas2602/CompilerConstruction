package writer;

import ast.AbstractSyntaxTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CodeGenerator {

    public static void CreateIntermediate(AbstractSyntaxTree InRoot, String InFileName) throws IOException {
        GeneratorSlave slave = new GeneratorSlave();
        InRoot.CreateSnippet(slave, null);

        File file = CreateFile(InFileName);
        Files.write(file.toPath(), slave.Serialize());
    }

    public static File CreateFile(String InFileName) throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        String relativePath = "res" + fileSeparator + "llvm" + fileSeparator + InFileName + ".ll";
        File file = new File(relativePath);
        if (file.createNewFile()) {
            System.out.println(relativePath + " File Created in Project root directory");
        } else {
            System.out.println("File " + relativePath + " already exists");
        }

        return file;
    }
}
