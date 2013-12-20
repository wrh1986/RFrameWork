package com.RFramework.utils.script.loader;

import java.io.StringReader;

import com.RFramework.utils.script.Script;


public class DefaultScriptLoader implements ScriptLoader{

  private String context;
  private Script.Type scriptType;
  

  @Override
  public Script loadResource() {
    Script result = new Script();
    result.setScriptReader(new StringReader(context));
    result.setScriptType(scriptType);
    return result;
  }
  
  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public DefaultScriptLoader(String context, Script.Type scriptType) {
    this.context = context;
    this.scriptType = scriptType;
  }
  

  public Script.Type getScriptType() {
    return scriptType;
  }

  public void setScriptType(Script.Type scriptType) {
    this.scriptType = scriptType;
  }

}
