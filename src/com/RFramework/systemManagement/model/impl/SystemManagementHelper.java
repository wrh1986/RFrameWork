package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.service.PageItemService;
import com.RFramework.systemManagement.service.UserService;

public class SystemManagementHelper {

  private PageItemService pageItemService;
  private UserService userService;
  public PageItemService getPageItemService() {
    return pageItemService;
  }
  public void setPageItemService(PageItemService pageItemService) {
    this.pageItemService = pageItemService;
  }
  public UserService getUserService() {
    return userService;
  }
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  
  public void loadUser(User user) {
    
  }
  
}
