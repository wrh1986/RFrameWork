package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;
import com.RFramework.systemManagement.model.Item;

public abstract class AbstractItem implements Item {

  private long uid;
  private String name;
  private Auth auth;

  public AbstractItem(Auth auth) {
    if(null == auth) {
      this.auth = Auth.NOAUTH;
    } else {
      this.auth = auth;
    }

  }

  public Auth getAuth() {
    return this.auth;
  }
  public void setAuth(Auth auth) {
    this.auth = auth;
  }

  public long getUid() {
    return this.uid;
  }
  public void setUid(long uid) {
    this.uid = uid;
  }

  public String getName() {
    return this.name;
  }
  public void setName(String name) {
    this.name = name;
  }
}
