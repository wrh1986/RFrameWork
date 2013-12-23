package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.service.PageItemService;
import com.RFramework.systemManagement.service.RoleService;
import com.RFramework.systemManagement.service.UserService;

public class SystemManagementHelper {

  private PageItemService pageItemService;
  private UserService userService;
  private RoleService roleService;
  
  public boolean checkUser(User user) {
    if(user.isReset()) {
      //TODO load user information, including the related info
      return false;
    }
    return true;
  }

  public static void invalidUser (User user) {
    user.setReset(true);
  }


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

  public RoleService getRoleService() {
    return roleService;
  }

  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }
}
