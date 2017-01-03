package com.javase.corejavaii.compile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by root on 1/3/17.
 */
public class JavaCompileTest {
    public static void main(String[] args) throws FileNotFoundException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        OutputStream outputStream = new FileOutputStream("src/main/java/com/javase/corejavaii/compile/App.class");
        OutputStream errputStream = System.err;
        int result = compiler.run(null, outputStream, errputStream, "/var/root/work/git/java-tutorial/corejava/src/main/java/com/javase/App.java");
        System.out.println("result : " + result);
    }
}
