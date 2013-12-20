package com.RFramework.common.dao;

import org.springframework.jdbc.core.JdbcTemplate;


public class BaseDao {

  protected JdbcTemplate jdbcTemplate;
  
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }  
}
