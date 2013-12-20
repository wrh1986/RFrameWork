package com.RFramework.systemManagement.dao.mysqlImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.RFramework.common.dao.BaseDao;
import com.RFramework.pojo.PageItemBean;
import com.RFramework.systemManagement.dao.PageItemDao;

public class PageItemDaoImpl extends BaseDao implements PageItemDao {

  @Override
  public void add(PageItemBean item) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void update(PageItemBean item) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void delete(PageItemBean item) {
    // TODO Auto-generated method stub
    
  }

  public List<PageItemBean> queryForList(Map params) {
    List<PageItemBean> result = null ;
    if(null != params) {
      StringBuilder sql = new StringBuilder("select * from sys_pageitem where 1=1 order by sequence asc");

      result = super.jdbcTemplate.query(sql.toString(), new PageItemRowMapper());
    }
    if(null == result) result = new ArrayList<PageItemBean>();
    return result;
  }

  @Override
  public PageItemBean getPageItemById(long uid) {
    String sql = "select * from sys_pageitem where uid=" + uid;
    PageItemBean result = (PageItemBean)super.jdbcTemplate.queryForObject(sql, new PageItemRowMapper());
    return result;
  }
  
  class PageItemRowMapper implements RowMapper{

    @Override
    public PageItemBean mapRow(ResultSet rs, int arg1) throws SQLException {
      PageItemBean item = new PageItemBean();
      item.setUid(rs.getLong("uid"));
      item.setName(rs.getString("name"));
      item.setUrl(rs.getString("url"));
      item.setType(com.RFramework.systemManagement.model.PageItemType.parseType(rs.getInt("type")));
      item.setParentId(rs.getLong("parentId"));
      item.setSequence(rs.getInt("sequence"));
      item.setDescription(rs.getString("description"));
      return item;
    }
    
  }
}
