package writer;

import ast.AbstractSyntaxTree;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CodeGenerator {

    public static void CreateIntermediate(AbstractSyntaxTree root, AbstractSyntaxTree std, String fileName, boolean silent) throws IOException {
        GeneratorSlave slave = new GeneratorSlave();
        std.CreateSnippet(slave, null);
        root.CreateSnippet(slave, null);

        File file = CreateFile(fileName, silent);
        Files.write(file.toPath(), slave.Serialize());
    }

    public static File CreateFile(String fileName, boolean silent) throws IOException {
        File file = new File(fileName);
        if (file.createNewFile()) {
            if (!silent) {
                System.out.println("File " + fileName + " created");
            }
        } else {
            if (!silent) {
                System.out.println("File " + fileName + " already exists");
            }
        }

        return file;
    }
}
