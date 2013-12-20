package com.RFramework.classloader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.log4j.helpers.Loader;

public class TestClassLoader {

  public static void main(String[] args) throws ClassNotFoundException, IOException {
    URL[] urls = {
        new URL("file:\\E:\\")
    };
    ClassLoader loader = new URLClassLoader(urls);
    Class a = loader.loadClass("com.rframework.A.A");
    Class b = loader.loadClass("com.rframework.B.B");
    System.out.println();
  }
}
