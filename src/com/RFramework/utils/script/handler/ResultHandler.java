package com.RFramework.utils.script.handler;

import java.io.Writer;
import java.util.List;
import java.util.Map;

public interface ResultHandler {

  public List<String> getParameterNames();
  public void handleResult(Map<String, Object> paraMap);
  public Writer getWriter();
  public Writer getErrorWriter();
}
