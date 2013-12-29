package com.RFramework.systemManagement.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.RFramework.pojo.PageItemBean;
import com.RFramework.systemManagement.dao.PageItemDao;
import com.RFramework.systemManagement.service.PageItemService;
import com.RFramework.utils.db.UidHelper;

public class PageItemServiceImpl implements PageItemService {

  private PageItemDao pageItemDao;
  
  public void save(PageItemBean item) {
    if(null == item.getUid()) {
      this.insert(item);
    } else {
      this.update(item);
    }
  }
  
  @Override
  public void insert(PageItemBean item) {
    item.setUid(UidHelper.get());
    this.pageItemDao.insert(item);
  }

  @Override
  public void update(PageItemBean item) {
    this.pageItemDao.update(item);
  }

  @Override
  public void delete(PageItemBean item) {
    this.pageItemDao.delete(item.getUid());
  }

  @Override
  public List<PageItemBean> query(Map params) {
    // TODO Auto-generated method stub
    return this.pageItemDao.queryForList(params);
  }

  public List<PageItemBean> formatTreeList(List<PageItemBean> pageItemList) {
    List<PageItemBean> result = new ArrayList<PageItemBean>();
    Map<Long, List<PageItemBean>> itemMap = new HashMap<Long, List<PageItemBean>>();
    
    for(PageItemBean bean: pageItemList) {
      if(bean.getParentId() == null || bean.getParentId() == 0) {
        result.add(bean);
      } else {
        if(itemMap.get(bean.getParentId()) != null) {
          itemMap.get(bean.getParentId()).add(bean);
        } else {
          List<PageItemBean> itemList = new ArrayList<PageItemBean>();
          itemList.add(bean);
          itemMap.put(bean.getParentId(), itemList);
        }
      }
    }
    
    for(PageItemBean bean: result) {
      prepareChildren(bean, itemMap);
    }
    
    return result;
  }
  
  private void prepareChildren(PageItemBean bean, Map<Long, List<PageItemBean>> itemMap) {
    if(itemMap.get(bean.getUid()) != null) {
      bean.setChildren(itemMap.get(bean.getUid()));
      for(PageItemBean b : itemMap.get(bean.getUid())) {
        prepareChildren(b, itemMap);
      }
    }
  }
  public PageItemDao getPageItemDao() {
    return pageItemDao;
  }

  public void setPageItemDao(PageItemDao pageItemDao) {
    this.pageItemDao = pageItemDao;
  }

  @Override
  public PageItemBean getPageItemById(long uid) {
    return this.pageItemDao.getPageItemById(uid);
  }

}
