package com.RFramework.systemManagement.service;

import com.RFramework.pojo.UserBean;

public interface UserService {
  public UserBean getUserByUsername(String userName);
  public boolean authenticate(String userName, String password);
}
