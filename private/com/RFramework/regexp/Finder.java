package com.RFramework.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
  public String findAbnormalEnd(String input) {
    String regEx = "(.*)(<|<b|<br|&|&n|&nb|&nbs|&nbsp)$";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(input);
    if(m.find()) {
      input = m.group(m.groupCount() - 1);
    }
    String regEx2 = "([\\s\\S]*)(\\{[^\\}]*)$";
    p = Pattern.compile(regEx2);
    m = p.matcher(input);
    if(m.find()) {
      input = m.group(m.groupCount() - 1);
    }
    return input;
  }
}
