package com.RFramework.utils.common;

public class TreeHelper {
  private enum PageItemType {

    L(1),F(0);
    private int type;
    private PageItemType(int i) {
      type = i;
    }
    public int getTypeCode() {
      return type;
    }
    public static PageItemType getDefault() {
      return PageItemType.L;
    }
    public static PageItemType parseType(int i){
      for(PageItemType t: PageItemType.values()) {
        if(t.type == i) {
          return t;
        }
      }
      return PageItemType.getDefault();
    }
    public static PageItemType parseType(String i){
      for(PageItemType t: PageItemType.values()) {
        if(t.toString().equals(i)) {
          return t;
        }
      }
      return PageItemType.getDefault();
    }
  }
  
  public static String parseNodeType(int type) {
    return PageItemType.parseType(type).toString();
  }
  
  public static int parseNodeType (String type) {
    return PageItemType.parseType(type).getTypeCode();
  }
}
