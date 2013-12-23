package com.RFramework.systemManagement.action;

import com.RFramework.systemManagement.service.RoleService;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Created by wangronghua on 13-12-20.
 */
public class RoleSubmitAction extends ActionSupport {

  private RoleService roleService;

  private long uid;
  private String name;
  private String description;

  public String save() {
    return SUCCESS;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getUid() {
    return uid;
  }

  public void setUid(long uid) {
    this.uid = uid;
  }

  public RoleService getRoleService() {
    return roleService;
  }

  public void setRoleService(RoleService roleService) {
    this.roleService = roleService;
  }
}
