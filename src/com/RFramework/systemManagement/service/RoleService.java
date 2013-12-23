package com.RFramework.systemManagement.service;

import com.RFramework.pojo.RoleBean;

import java.util.List;
import java.util.Map;

/**
 * Created by wangronghua on 13-12-20.
 */
public interface RoleService {
  public RoleBean getRoleById(long uid);
  public RoleBean getRoleByName(String name);
  public List<RoleBean> query(Map paraMap);
  public List<RoleBean> queryByUser(long userId);
  public void insert(RoleBean roleBean);
  public void update(RoleBean roleBean);
  public void delete(long uid);

  public void addUserToRole(long roleId, long userId);
  public void removeUserFromRole(long roleId, long userId);
  public void removeUserFromAllRoles(long userId);
}
