package com.RFramework.compiler;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;


public class Compiler {

  public void compile(String filename, String jarPath, String desDir) {
    String[] compilerArgs = {
      "-sourcepath", desDir,
      "-classpath", jarPath,
      "-target", "1.7",
      filename };

    ByteArrayOutputStream bos = null;
    PrintWriter pw = null;
    int status;

    try {
      bos = new ByteArrayOutputStream();
      pw = new PrintWriter (bos);
      
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler(); 
      compiler.run(null, null, null, compilerArgs);  
    } catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static void main(String[] args) {
	String filename = "/tek/workspace/MyWeb/src/compiler/Compiler.java";
	String jarPath = "";
	String desDir = "/tek/";
	Compiler c = new Compiler();
	c.compile(filename, jarPath, desDir);
  }
}
