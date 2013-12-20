package com.RFramework.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

  public static void copyFile(File srcFile, File destFile) throws IOException{
    FileInputStream in = new FileInputStream(srcFile);
    FileOutputStream out = new FileOutputStream(destFile);
    byte[] buffer = new byte[1024];
    int len = 0;
    while((len = in.read(buffer)) != -1) {
      out.write(buffer, 0, len);
    }
    out.flush();
    out.close();
    in.close();
  }
}
