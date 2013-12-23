package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;

import java.util.ArrayList;
import java.util.List;

public class User {

  private List<Role> roleList;
  private boolean isReset = true;

  public void addRole(Role role) {
    if(null == roleList) {
      roleList = new ArrayList<Role>();
    }
    this.roleList.add(role);
  }

  public Auth getAuth(long itemUid) {
      if(roleList == null) return Auth.NOAUTH;
      List<Auth> aList = new ArrayList<>();
      for(Role r: roleList) {
        aList.add(r.getAuth(itemUid));
      }
      return AuthHelper.getMaxAuth(aList);
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    if(null != roleList) {
      if( null != this.roleList) {
        for(Role r: roleList) {
          this.addRole(r);
        }
      } else {
        this.roleList = roleList;
      }
    }

  }

  public boolean isReset() {
    return isReset;
  }

  public void setReset(boolean isReset) {
    this.isReset = isReset;
  }
}
