package com.RFramework.systemManagement.service.impl;

import com.RFramework.pojo.RoleBean;
import com.RFramework.systemManagement.service.RoleService;

import java.util.List;
import java.util.Map;

/**
 * Created by wangronghua on 13-12-20.
 */
public class RoleServiceImpl implements RoleService {
  @Override
  public RoleBean getRoleById(long uid) {
    return null;
  }

  @Override
  public RoleBean getRoleByName(String name) {
    return null;
  }

  @Override
  public List<RoleBean> query(Map paraMap) {
    return null;
  }

  @Override
  public List<RoleBean> queryByUser(long userId) {
    return null;
  }

  @Override
  public void insert(RoleBean roleBean) {

  }

  @Override
  public void update(RoleBean roleBean) {

  }

  @Override
  public void delete(long uid) {

  }

  @Override
  public void addUserToRole(long roleId, long userId) {

  }

  @Override
  public void removeUserFromRole(long roleId, long userId) {

  }

  @Override
  public void removeUserFromAllRoles(long userId) {

  }
}
