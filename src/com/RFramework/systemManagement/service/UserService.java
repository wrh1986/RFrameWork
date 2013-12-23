package com.RFramework.systemManagement.service;

import com.RFramework.pojo.UserBean;

import java.util.List;
import java.util.Map;

public interface UserService {
  public UserBean getUserById(long uid);
  public UserBean getUserByName(String userName);
  public List<UserBean> query(Map paraMap);
  public List<UserBean> queryByRole(long roleId);
  public void insert(UserBean user);
  public void update(UserBean user);
  public void delete(long uid);

  public boolean authenticate(String userName, String password);
}
