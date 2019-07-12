package test;

// General stuff
import org.bytedeco.javacpp.*;

// Headers required by LLVM
import org.bytedeco.llvm.LLVM.*;
import java.io.File;

import static org.bytedeco.llvm.global.LLVM.*;

public class LocalTester {

    private BytePointer m_Error;
    private LLVMContextRef m_Context;

    public LocalTester() {
        m_Error = new BytePointer((Pointer)null); // Used to retrieve messages from functions
        LLVMLinkInMCJIT();
        LLVMInitializeNativeAsmPrinter();
        LLVMInitializeNativeAsmParser();
        LLVMInitializeNativeDisassembler();
        LLVMInitializeNativeTarget();
    }

    public void compile(String fileName) {
        m_Context = LLVMContextCreate();

        //get file size
        String fileSeparator = System.getProperty("file.separator");
        String relativePath = "res" + fileSeparator + "llvm" + fileSeparator + fileName + ".ll";
        File file = new File(relativePath);

        long size = file.length();

        //load file

        LLVMMemoryBufferRef buf = new LLVMMemoryBufferRef();
        BytePointer path = new BytePointer(relativePath);
        int res = LLVMCreateMemoryBufferWithContentsOfFile(path, buf, m_Error);
        if(res == 1) {
            System.out.println("Error while loading file!");
            onError();
        }

        //parse IR

        LLVMModuleRef mod = new LLVMModuleRef();
        res = LLVMParseIRInContext(m_Context, buf, mod, m_Error);
        if(res == 1) {
            System.out.println("Error while parsing IR");
            onError();
        }

        //verify module

        LLVMVerifyModule(mod, LLVMAbortProcessAction, m_Error);
        LLVMDisposeMessage(m_Error);

        //create execute engine

        LLVMExecutionEngineRef engine = new LLVMExecutionEngineRef();
        if(LLVMCreateJITCompilerForModule(engine, mod, 2, m_Error) != 0) {
            System.out.println("Error while creating execution engine");
            LLVMDisposeModule(mod);
            onError();
        }

        //fine main function
        LLVMValueRef main = new LLVMValueRef();
        BytePointer mainptr = new BytePointer("main");
        res = LLVMFindFunction(engine, mainptr, main);
        if(res == 1) {
            System.out.println("Error while searching main function");
            System.exit(0);
        }

        LLVMGenericValueRef exec_res = LLVMRunFunction(engine, main, 0, new LLVMGenericValueRef());

        System.out.println("Result: " + LLVMGenericValueToInt(exec_res, 0));
        LLVMDisposeExecutionEngine(engine);
        LLVMContextDispose(m_Context);
    }

    public void onError() {
        System.out.println(m_Error.getString());
        LLVMDisposeMessage(m_Error);
        LLVMContextDispose(m_Context);
        System.exit(0);
    }
}
