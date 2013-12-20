package com.RFramework.utils.script;

import java.io.Reader;

public class Script {

  public boolean validate() {
    if(null != scriptType && null != scriptReader ) {
      return true;
    } else {
      return false;
    }
  }
  
  public enum Type {
    JAVASCRIPT("javascript"), 
    PYTHON("python"), 
    RUBY("ruby");
    private String value;
    public String value() {
    	return value;
    }
    Type(String value) {
    	this.value = value;
    }
  }
  private String name;
  private Type scriptType;
  private String scriptBody;
  private Reader scriptReader;
  public String getName() {
    return name;
  }
  public Type getScriptType() {
    return scriptType;
  }
  public String getScriptBody() {
    return scriptBody;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setScriptType(Type scriptType) {
    this.scriptType = scriptType;
  }
  public void setScriptBody(String scriptBody) {
    this.scriptBody = scriptBody;
  }
  public Reader getScriptReader() {
    return scriptReader;
  }
  public void setScriptReader(Reader scriptReader) {
    this.scriptReader = scriptReader;
  }
}
