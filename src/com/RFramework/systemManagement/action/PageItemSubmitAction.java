package com.RFramework.systemManagement.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;

import com.RFramework.common.app.AppConstants;
import com.RFramework.pojo.PageItemBean;
import com.RFramework.systemManagement.service.PageItemService;
import com.opensymphony.xwork2.ActionSupport;

public class PageItemSubmitAction extends ActionSupport{

  /**
   * 
   */
  private static final long serialVersionUID = 6558391877434992570L;

  private PageItemService pageItemService;
  
  public String save(){
    pageItemService.save(getBean());
    HttpServletRequest request = ServletActionContext.getRequest();
    request.setAttribute(AppConstants.ID, this.uid);
    request.setAttribute(AppConstants.ACTION, AppConstants.ACTION_MODIFY);
    return SUCCESS;
  }
  
  private PageItemBean getBean() {
    PageItemBean item;
    if(uid != null) {
      item = pageItemService.getPageItemById(uid);
      BeanUtils.copyProperties(this, item);
    } else {
      item = new PageItemBean();
      item.setName(name);
      item.setUrl(url);
      item.setDescription(description);
    }
    return item;
  }
  
  private Long uid;
  private String name;
  private String description;
  //private String parentId;
  private String url;
  public Long getUid() {
    return uid;
  }
  public void setUid(Long uid) {
    this.uid = uid;
  }
  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public PageItemService getPageItemService() {
    return pageItemService;
  }
  public void setPageItemService(PageItemService pageItemService) {
    this.pageItemService = pageItemService;
  }
}
