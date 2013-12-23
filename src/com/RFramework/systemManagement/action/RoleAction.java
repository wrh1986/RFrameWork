package com.RFramework.systemManagement.action;

import com.RFramework.pojo.RoleBean;
import com.RFramework.systemManagement.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by wangronghua on 13-12-20.
 */
public class RoleAction extends ActionSupport {
  private RoleService roleService;
  private List<RoleBean> roleBeanList;
  private long roleId;
  private long userId;
  private List authList;

  public String getAllRoles() {
    return SUCCESS;
  }
  public String addUser() {
    return SUCCESS;
  }

  public String removeUser() {
    return SUCCESS;
  }

  public String saveAuths() {
    return SUCCESS;
  }

  public String delete() {
    return SUCCESS;
  }

  public RoleService getRoleService() {
    return roleService;
  }

  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }

  public List<RoleBean> getRoleBeanList() {
    return roleBeanList;
  }

  public void setRoleBeanList(List<RoleBean> roleBeanList) {
    this.roleBeanList = roleBeanList;
  }

  public long getRoleId() {
    return roleId;
  }

  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public List getAuthList() {
    return authList;
  }

  public void setAuthList(List authList) {
    this.authList = authList;
  }
}
