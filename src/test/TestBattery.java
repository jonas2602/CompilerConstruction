package test;

import ast.BlockNode;
import ast.ProgramNode;
import ast.core.StdLib;
import gen.PascalLexer;
import gen.PascalParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import visitors.ProgramVisitor;
import writer.CodeGenerator;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestBattery {
    private static final String TESTFILENAME = "test_battery";
    private static final String PROCESSCLASSNAME = "test.TestProcess";

    private List<TestCase> m_TestCases;
    private int m_Passed;
    private int m_Failed;

    public TestBattery() {
        m_TestCases = new ArrayList<>();

        add("arrays").AddLines("a aba 1.000000", "afgh");
        add("branch").AddSeperatorLines("1000 2 0 -1");
        add("constant").AddSeperatorLines("0 1 2 3 4 5 5");
        add("enum").AddSeperatorLines("6 0 2").AddLines("Not start", "middle");
        add("goto").AddSeperatorLines("0 2");
        add("loops").AddSeperatorLines("0 1 2 3 4 5 6 7 8 9 10 11 12").AddLines(" ").AddSeperatorLines("0 1 2 3 4 5 6 7 8 9 10 11").AddLines(" ").AddSeperatorLines("0 1 2 3 4 5 6 7 8 9 10 11 12");
        add("memory").AddSeperatorLines("42 99 12");
        add("pointer").AddLines("0 10 2 3 4, 10" ,"3");
        add("string").AddLines("test my random string test123");
        add("innerfunctions").AddSeperatorLines("1 1 1");
        add("innerfunctions2").AddLines("4");
        add("switch").AddLines("MEH");
        // TODO
        add("sets");
        add("types").AddSeperatorLines("3 10");
    }

    public TestCase add(String name) {
        TestCase t = new TestCase(name);
        m_TestCases.add(t);
        return t;
    }

    public void Fire() {
        String fileSeparator = System.getProperty("file.separator");
        String testFileName = "res" + fileSeparator + "llvm" + fileSeparator + TESTFILENAME + ".ll";
        File testFile = new File(testFileName);

        String javaHome = System.getProperty("java.home") + fileSeparator + "bin" + fileSeparator + "java";

        String libFolder = "lib" + fileSeparator + "llvm" + fileSeparator;
        File libFile = new File(libFolder);

        String classpath = null;
        try {
            classpath = new File(TestBattery.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getAbsolutePath()+";"+libFile.getAbsolutePath()+fileSeparator + "*";
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(0);
        }

        String[] cmd = new String[]{javaHome, "-cp", classpath, PROCESSCLASSNAME, testFile.getAbsolutePath()};

        m_Failed = 0;
        m_Passed = 0;
        long time = System.currentTimeMillis();

        for(TestCase c: m_TestCases) {
            String relativePath = "res" + fileSeparator + "examples" + fileSeparator + "tests" +fileSeparator + c.GetFileName() + ".pas";
            File file = new File(relativePath);

            if(!file.exists() || !file.isFile()) {
                PrintError(c, "File not found");
                continue;
            }



            //check if it can be moved up
            BlockNode stdBlock = StdLib.CreateStdLib();

            PascalLexer lexer = null;
            try {
                lexer = new PascalLexer(CharStreams.fromFileName(file.getAbsolutePath()));
            } catch (IOException e) {
                PrintError(c, e.getMessage());
                continue;
            }
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Build parse tree
            PascalParser parser = new PascalParser(tokens);
            ParseTree tree = parser.program();

            if (parser.getNumberOfSyntaxErrors() != 0) {
                PrintError(c,"Error while parsing file!");
                continue;
            }

            ProgramVisitor vis = new ProgramVisitor();
            ProgramNode prog = vis.visit(tree);
            prog.SetParent(stdBlock);


            stdBlock.CheckType();
            prog.CheckType();

            try {
                CodeGenerator.CreateIntermediate(prog, stdBlock, TESTFILENAME);
            } catch (IOException e) {
                PrintError(c, e.getMessage());
                continue;
            }

            //Test if it can be executed an is runnable
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(cmd);
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();
                BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                int counter = 0;
                List<String> expected = c.GetLines();
                String error = "";
                String line = null;
                boolean checkError = false;
                while ((line = stdout.readLine()) != null)  {
                    if (!line.equals(expected.get(counter))) {
                        if (counter == 0) {
                            error = line;
                            break;
                        }
                        System.out.println("Expected "+expected.get(counter)+" got "+line+" for input "+counter);
                        checkError = true;
                    }

                    counter++;
                }

                if (counter != 0 && counter != expected.size()) {
                    PrintError(c,"Missing "+(expected.size() - counter)+" output(s)");
                }

                if (checkError) {
                    PrintError(c,"Logic Failed");
                }

                int ret = process.waitFor();
                if (ret != 0) {
                    PrintError(c, "Process finished with errorcode "+ret+" and error: "+error);
                    System.err.println("stdout output");
                    while ((line = stdout.readLine()) != null)  {
                        System.err.println(line);
                    }

                    System.err.println("stderr output");
                    while ((line = stderr.readLine()) != null)  {
                        System.err.println(line);
                    }
                    continue;
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

            PrintPassed(c);
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Test summary");
        System.out.println("Tested cases: "+ m_TestCases.size());
        System.out.println("Passed cases: "+ m_Passed);
        System.out.println("Failed cases: "+ m_Failed);
        System.out.println("Complete test took "+((System.currentTimeMillis() - time)/1000) +" seconds");

        if (testFile.exists()) {
            testFile.delete();
        }
    }

    public void PrintPassed(TestCase c) {
        System.out.println("[PASSED TEST "+c.GetFileName()+"]");
        m_Passed++;
    }

    public void PrintError(TestCase c, String reason) {
        System.err.println("[FAILED TEST "+c.GetFileName()+"] "+reason);
        m_Failed++;
    }
}
