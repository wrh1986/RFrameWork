package test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.CharBuffer;
import java.util.Calendar;
import java.util.Date;

public class Test {

  public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
//    ClassLoader loader1 = new URLClassLoader(new URL[0]);
//    ClassLoader loader2 = new URLClassLoader(new URL[0], null);
//    ClassLoader loader3 = loader2;
//    Class string1 = Test.class.getClassLoader().loadClass("java.lang.String");
//    Class String2 = loader1.loadClass("java.lang.String");
//    Class String3 = loader2.loadClass("java.lang.String");
    
    Runtime rt = Runtime.getRuntime();
    Process process1 = rt.exec("ssh 10.60.25.81 'ls'");
    InputStreamReader reader = new InputStreamReader(process1.getInputStream());
    process1.waitFor();
    char[] chars = new char[1000];
    reader.read(chars);
    //process1.destroy();
    System.out.println(chars);
  }
}
