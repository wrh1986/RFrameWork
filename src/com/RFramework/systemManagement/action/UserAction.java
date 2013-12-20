package com.RFramework.systemManagement.action;

import java.util.ArrayList;
import java.util.List;

import com.RFramework.pojo.UserBean;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{

  /**
   * 
   */
  private static final long serialVersionUID = -8700576628989623264L;
  
  private List<UserBean> items;
  
  public List<UserBean> getItems() {
    return items;
  }
  public void setItems(List<UserBean> items) {
    this.items = items;
  }
  /**
   * @return returns {@link #SUCCESS}
   * @throws Exception can be thrown by subclasses.
   */
  public String getAllUsers() throws Exception {
    this.initItem();
    return SUCCESS;
  }
  
  private void initItem() {
    items = new ArrayList<UserBean>(20);
    for(int index = 0; index < 20; index ++) {
      UserBean u = new UserBean();
      u.setName("user"+index);
      u.setUserName("userName"+index);
      u.setFormatedCreateTime("2013-01-01 00:00:00");
      u.setFormatedLastLoginTime("2013-01-01 00:00:00");
      items.add(u);
    }
  }
}
