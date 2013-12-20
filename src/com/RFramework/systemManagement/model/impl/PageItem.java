package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;

public class PageItem extends AbstractItem {

  private Auth auth;
  public PageItem() {
    this.auth = Auth.NOAUTH;
  }
  public PageItem(Auth auth){
    if(auth == null) {
      this.auth = Auth.NOAUTH;
    }
    this.auth = auth;
  }
  
  @Override
  public Auth getAuth() {
    // TODO Auto-generated method stub
    return this.auth;
  }
}
