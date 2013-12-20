package com.RFramework.systemManagement.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.RFramework.common.app.AppConstants;
import com.RFramework.systemManagement.service.PageItemService;
import com.opensymphony.xwork2.ActionSupport;

public class PageItemSubmitAction extends ActionSupport{

  /**
   * 
   */
  private static final long serialVersionUID = 6558391877434992570L;

  private PageItemService pageItemService;
  
  public String save(){
    HttpServletRequest request = ServletActionContext.getRequest();
    request.setAttribute(AppConstants.ID, this.uid);
    request.setAttribute(AppConstants.ACTION, AppConstants.ACTION_MODIFY);
    return SUCCESS;
  }
  private String uid;
  private String name;
  private String description;
  private String type;
  public String getUid() {
    return uid;
  }
  public void setUid(String uid) {
    this.uid = uid;
  }
  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  public String getType() {
    return type;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public void setType(String type) {
    this.type = type;
  }
  public PageItemService getPageItemService() {
    return pageItemService;
  }
  public void setPageItemService(PageItemService pageItemService) {
    this.pageItemService = pageItemService;
  }
}
