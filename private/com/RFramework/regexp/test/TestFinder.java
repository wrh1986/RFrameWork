package com.RFramework.regexp.test;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.RFramework.regexp.Finder;



public class TestFinder{
  
  public static void main(String[] args) {
    Finder f = new Finder();
    String input0 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;&";
    String input1 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;&n";
    String input2 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;&nb";
    String input3 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;&nbs";
    String input4 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;&nbsp";
    String input5 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;&nbsp;";
    String input6 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;<";
    String input7 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;<b";
    String input8 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;<br";
    String input9 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;<br>";
    String input10 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;<br>{";
    String input11 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;<br>{sd";
    String input12 = "ajkdhjiash21i9ksakdajkld1ko4j323kl<br>nbsp;nbsp;<br>{adad}";
    String input13 = "ajkdhjiash21i9ksakd{ajkl}d1ko4j323kl<br>nbsp;nbsp;<br>{";
    String input14 = "ajkdhjiash21i9k\nsakd{auditLog\n";
    String input15 = "ajkdhjiash21i9ksakd{ajkl}d1ko4j323kl<br>nbsp;nbsp;<br>{fsdffs{as.sajdha.kasjd";
//    System.out.println(f.findAbnormalEnd(input0));
//    System.out.println(f.findAbnormalEnd(input1));
//    System.out.println(f.findAbnormalEnd(input2));
//    System.out.println(f.findAbnormalEnd(input3));
//    System.out.println(f.findAbnormalEnd(input4));
//    System.out.println(f.findAbnormalEnd(input5));
//    System.out.println(f.findAbnormalEnd(input6));
//    System.out.println(f.findAbnormalEnd(input7));
//    System.out.println(f.findAbnormalEnd(input8));
//    System.out.println(f.findAbnormalEnd(input9));
//    System.out.println(f.findAbnormalEnd(input10));
//    System.out.println(f.findAbnormalEnd(input11));
//    System.out.println(f.findAbnormalEnd(input12));
//    System.out.println(f.findAbnormalEnd(input13));
//    System.out.println(f.findAbnormalEnd(input14));
//    System.out.println(f.findAbnormalEnd(input15));
//    String input17 = " xxx{auditLog.associate} Policy Policy-1\n"+
// "{auditLog.associate} Policy Policy-100\n" +
// "{auditLog.associate} Policy Policy-101\n" +
// "{auditLog.associate} Policy Policy-102\n" +
// "{auditLog.associate} Policy Policy-103\n" +
// "{auditLog.associate} Policy Policy-104\n" +
// "{auditLog.associate} Policy Policy-105\n" +
// "{auditLog.associate} Policy Policy-106\n" +
// "{auditLog.associate} Policy Policy-107\n" +
// "{auditLog.assoc";
//    System.out.println(f.findAbnormalEnd(input17));
    float f1 = 99.9999965f;
    System.out.println(f1);
  }
  
}
