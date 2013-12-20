package com.RFramework.utils.script.handler;

import java.io.Writer;
import java.util.List;
import java.util.Map;

public class DefaultResultHandler implements ResultHandler{

  @Override
  public List<String> getParameterNames() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void handleResult(Map<String, Object> paraMap) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Writer getWriter() {
    // TODO Auto-generated method stub
    return writer;
  }
  
  public void setWriter(Writer writer){
    this.writer = writer;
  }
  
  private Writer writer;
  private Writer errWriter;

  @Override
  public Writer getErrorWriter() {
    // TODO Auto-generated method stub
    return errWriter;
  }
  
  public void setErrorWriter(Writer writer) {
    // TODO Auto-generated method stub
    this.errWriter = writer;
  }

}
