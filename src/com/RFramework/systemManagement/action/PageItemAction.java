package com.RFramework.systemManagement.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.RFramework.common.app.AppConstants;
import com.RFramework.pojo.PageItemBean;
import com.RFramework.systemManagement.service.PageItemService;
import com.opensymphony.xwork2.ActionSupport;

public class PageItemAction extends ActionSupport{

  /**
   * 
   */
  private static final long serialVersionUID = 6722741674137689588L;
  
  private PageItemService pageItemService;
  private List<PageItemBean> items;
  private String identifier = "uid";
  private long parentId;
  private String label = "name";
  private PageItemBean pageItem;
  private String lastSelected="root";
  private String pageAction = "NONE";
  public List<PageItemBean> getItems() {
    return items;
  }
  
  public String getIdentifier() {
    return identifier;
  }
  
  public String getLabel(){
    return label;
  }
  
  public String getAllPages(){
    items = this.pageItemService.formatTreeList(pageItemService.query(new HashMap()));
    return SUCCESS;
  }

  public String execute(){
    Logger logger = Logger.getLogger(getClass());
    logger.warn("Test Started******************************");
    HttpServletRequest request = ServletActionContext.getRequest();
    
    String idStr = request.getParameter(AppConstants.ID);
    if(idStr == null) {
      idStr = (String) request.getAttribute(AppConstants.ID);
    }
    
    String parentIdStr = request.getParameter(AppConstants.PARENT_ID);
    
    if(null != request.getAttribute(AppConstants.ACTION)){
      this.setPageAction((String) request.getAttribute(AppConstants.ACTION));
    }
    else this.setPageAction("NONE");

    long uid = null!=idStr?Long.valueOf(idStr):0l;
    if("view".equals(this.pageAction)){
      this.pageItem = this.pageItemService.getPageItemById(uid);
    } else if("modify".equals(this.pageAction)) {
      this.pageItem = this.pageItemService.getPageItemById(uid);
    } else if("create".equals(this.pageAction)) {
      setParentId(null!=parentIdStr?Long.valueOf(parentIdStr):0l);
    } else if("delete".equals(this.pageAction)) {
      this.pageItem = this.pageItemService.getPageItemById(uid);
      if(null != this.pageItem){
        this.pageItemService.delete(pageItem);
      }
    }
    return SUCCESS;
  }
  
  public void setPageItemService(PageItemService pageItemService) {
    this.pageItemService = pageItemService;
  }

  public PageItemBean getPageItem() {
    return pageItem;
  }

  public void setPageItem(PageItemBean pageItem) {
    this.pageItem = pageItem;
  }

  public String getPageAction() {
    return pageAction;
  }

  public void setPageAction(String pageAction) {
    this.pageAction = pageAction;
  }

  public String getLastSelected() {
    return lastSelected;
  }

  public void setLastSelected(String lastSelected) {
    this.lastSelected = lastSelected;
  }

  public long getParentId() {
    return parentId;
  }

  public void setParentId(long parentId) {
    this.parentId = parentId;
  }
}
