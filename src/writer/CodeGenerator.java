package writer;

import ast.AbstractSyntaxTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CodeGenerator {

    public static void CreateIntermediate(AbstractSyntaxTree root, String fileName) throws IOException {
        GeneratorSlave slave = new GeneratorSlave();
        root.CreateSnippet(slave, null);

        File file = CreateFile(fileName);
        Files.write(file.toPath(), slave.Serialize());
    }

    public static File CreateFile(String fileName) throws IOException {
        String fileSeparator = System.getProperty("file.separator");
        String relativePath = "res" + fileSeparator + "llvm" + fileSeparator + fileName + ".ll";
        File file = new File(relativePath);
        if (file.createNewFile()) {
            System.out.println(relativePath + " File Created in Project root directory");
        } else {
            System.out.println("File " + relativePath + " already exists");
        }

        return file;
    }
}
