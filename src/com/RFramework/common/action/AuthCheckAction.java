package com.RFramework.common.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.RFramework.common.app.AppConstants;
import com.RFramework.systemManagement.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class AuthCheckAction extends ActionSupport{

  /**
   * 
   */
  private static final long serialVersionUID = 2232138964838696155L;
  private String username;
  private String password;
  private UserService userService;
  public String check(){
    String result;
    if(userService.authenticate(username, password)) {
      HttpServletRequest request = ServletActionContext.getRequest();
      request.getSession().setAttribute(AppConstants.USERNAME, username);
      result = SUCCESS;
    } else {
      result = LOGIN;
    }
    return result;
  }
  public UserService getUserService() {
    return userService;
  }
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
