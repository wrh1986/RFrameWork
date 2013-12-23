package com.RFramework.systemManagement.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.RFramework.systemManagement.model.Auth;
import com.RFramework.systemManagement.model.Item;

public class Role {
  
  private List<Item> itemList;

  public List<Item> getItemList() {
    return itemList;
  }

  public void setItemList(List<Item> itemList) {
    this.itemList = itemList;
  }
  
  public void addItem(Item item){
    if(null == itemList){
      this.itemList = new ArrayList<Item>();
    }
    this.itemList.add(item);
  }
  
  public Auth getAuth(long itemUid) {
    for (Item item : itemList) {
      if (item.getUid() == itemUid) {
        return item.getAuth();
      }
    }
    return Auth.NOAUTH;
  }
}
