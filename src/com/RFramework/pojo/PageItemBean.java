package com.RFramework.pojo;

import java.io.Serializable;
import java.util.List;

import com.RFramework.systemManagement.model.PageItemType;

public class PageItemBean implements Serializable{

  /**
   * 
   */
  private static final long serialVersionUID = -579671848896044323L;
  private Long uid;
  private String name;
  private PageItemType type;
  private Long parentId;
  private String url;
  private int sequence;
  private String description;
  public Long getUid() {
    return uid;
  }
  public void setUid(Long uid) {
    this.uid = uid;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public PageItemType getType() {
    return type;
  }
  public void setType(PageItemType pageItemType) {
    this.type = pageItemType;
  }
  public Long getParentId() {
    return parentId;
  }
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  
  public List<PageItemBean> getChildren() {
    return children;
  }
  public void setChildren(List<PageItemBean> children) {
    this.children = children;
  }

  public int getSequence() {
    return sequence;
  }
  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  private List<PageItemBean> children;
}
