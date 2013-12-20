package com.RFramework.pojo;

import java.io.Serializable;

public class UserBean implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = 7834741985983361040L;
  
  private String name;
  private String userName;
  private String formatedLastLoginTime;
  private String formatedCreateTime;
  public String getName() {
    return name;
  }
  public String getUserName() {
    return userName;
  }
  public String getFormatedLastLoginTime() {
    return formatedLastLoginTime;
  }
  public String getFormatedCreateTime() {
    return formatedCreateTime;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setUserName(String userName) {
    this.userName = userName;
  }
  public void setFormatedLastLoginTime(String formatedLastLoginTime) {
    this.formatedLastLoginTime = formatedLastLoginTime;
  }
  public void setFormatedCreateTime(String formatedCreateTime) {
    this.formatedCreateTime = formatedCreateTime;
  }
}
