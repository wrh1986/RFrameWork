package com.RFramework.systemManagement.model;

public enum PageItemType {

  L(1),F(0);
  private int type;
  private PageItemType(int i) {
    type = i;
  }
  
  public static PageItemType parseType(int i){
    for(PageItemType t: PageItemType.values()) {
      if(t.type == i) {
        return t;
      }
    }
    return null;
  }
  
}
