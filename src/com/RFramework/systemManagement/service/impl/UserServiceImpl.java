package com.RFramework.systemManagement.service.impl;

import com.RFramework.pojo.UserBean;
import com.RFramework.systemManagement.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService{

  @Override
  public UserBean getUserById(long uid) {
    return null;
  }

  @Override
  public UserBean getUserByName(String userName) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UserBean> query(Map paraMap) {
    return null;
  }

  @Override
  public List<UserBean> queryByRole(long roleId) {
    return null;
  }

  @Override
  public void insert(UserBean user) {

  }

  @Override
  public void update(UserBean user) {

  }

  @Override
  public void delete(long uid) {

  }

  @Override
  public boolean authenticate(String userName, String password) {
    // TODO Auto-generated method stub
    return true;
  }

}
