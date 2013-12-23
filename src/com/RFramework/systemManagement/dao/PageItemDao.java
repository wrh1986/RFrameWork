package com.RFramework.systemManagement.dao;

import java.util.List;
import java.util.Map;

import com.RFramework.pojo.PageItemBean;

public interface PageItemDao {
  
  public void insert(PageItemBean item);
  public void update(PageItemBean item);
  public void delete(long uid);
  public PageItemBean getPageItemById(long uid);
  public List<PageItemBean> queryForList(Map params);
}
