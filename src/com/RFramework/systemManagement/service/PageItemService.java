package com.RFramework.systemManagement.service;

import java.util.List;
import java.util.Map;

import com.RFramework.pojo.PageItemBean;

public interface PageItemService {
  
  public void add(PageItemBean item);
  public void update(PageItemBean item);
  public void delete(PageItemBean item);
  public PageItemBean getPageItemById(long uid);
  public List<PageItemBean> queryForList(Map params);
  public List<PageItemBean> formatTreeList(List<PageItemBean> pageItemList);
}
