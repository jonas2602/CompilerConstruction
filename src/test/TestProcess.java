package test;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.llvm.LLVM.*;

import java.io.File;

import static org.bytedeco.llvm.global.LLVM.*;

public class TestProcess {
    public static void main(String args[]) {
        if(args.length != 1) {
            System.out.println("Please provide a path");
        }

        LLVMLinkInMCJIT();
        LLVMInitializeNativeAsmPrinter();
        LLVMInitializeNativeAsmParser();
        LLVMInitializeNativeDisassembler();
        LLVMInitializeNativeTarget();

        BytePointer error = new BytePointer((Pointer)null);;
        LLVMContextRef context = LLVMContextCreate();


        File file = new File(args[0]);
        if(!file.exists() || !file.isFile()) {
            System.out.println("Error while loading file!. File does not exist or is a directory");
            System.exit(1);
        }


        //load file
        LLVMMemoryBufferRef buf = new LLVMMemoryBufferRef();
        BytePointer path = new BytePointer(args[0]);
        int res = LLVMCreateMemoryBufferWithContentsOfFile(path, buf, error);
        if(res == 1) {
            System.out.println("Error while loading file!");
            onError(error);
        }

        //parse IR
        LLVMModuleRef mod = new LLVMModuleRef();
        res = LLVMParseIRInContext(context, buf, mod, error);
        if(res == 1) {
            System.out.println("Error while parsing IR");
            onError(error);
        }

        //verify module
        LLVMVerifyModule(mod, LLVMAbortProcessAction, error);
        LLVMDisposeMessage(error);

        //create execute engine
        LLVMExecutionEngineRef engine = new LLVMExecutionEngineRef();
        if(LLVMCreateJITCompilerForModule(engine, mod, 2, error) != 0) {
            System.out.println("Error while creating execution engine");
            LLVMDisposeModule(mod);
            onError(error);
        }

        //fine main function
        LLVMValueRef main = new LLVMValueRef();
        BytePointer mainptr = new BytePointer("main");
        res = LLVMFindFunction(engine, mainptr, main);
        if(res == 1) {
            System.out.println("Error while searching main function");
            System.exit(1);
        }

        LLVMGenericValueRef exec_res = LLVMRunFunction(engine, main, 0, new LLVMGenericValueRef());

        LLVMDisposeExecutionEngine(engine);
        LLVMContextDispose(context);

        System.exit(0);
    }

    public static void onError(BytePointer error) {
        System.out.println(error.getString());
        LLVMDisposeMessage(error);
        System.exit(1);
    }
}
