package com.RFramework.systemManagement.service;

import java.util.List;
import java.util.Map;

import com.RFramework.pojo.PageItemBean;

public interface PageItemService {
  
  public void insert(PageItemBean item);
  public void update(PageItemBean item);
  public void delete(PageItemBean item);
  public PageItemBean getPageItemById(long uid);

  public List<PageItemBean> query(Map params);
  public List<PageItemBean> formatTreeList(List<PageItemBean> pageItemList);
}
