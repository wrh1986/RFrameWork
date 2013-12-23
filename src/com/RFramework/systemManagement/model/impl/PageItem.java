package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;

public class PageItem extends AbstractItem {

  public PageItem() {
    super(Auth.NOAUTH);
  }
  public PageItem(Auth auth){
    super(auth);
  }
  
  @Override
  public Auth getAuth() {
    // TODO Auto-generated method stub
    return this.getAuth();
  }
}
