package com.RFramework.utils.script;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.RFramework.utils.script.Script.Type;

import com.RFramework.utils.script.handler.ResultHandler;
import com.RFramework.utils.script.loader.ScriptLoader;

public class ScriptRunner {
  private ScriptLoader scriptLoader;

  public ScriptRunner () {
    
  }
  public ScriptRunner (ScriptLoader loader) {
    this.scriptLoader = loader;
  }
  private void init() {
    
  }
  
  public Type convertType(String type) {
    Type[] types = Type.values();
    for(Type t: types) {
      if(t.value().equals(type)) {
        return t;
      }
    }
    return null;
  }
  
  private boolean validateEnv(ResultHandler handler) {
    if(null != scriptLoader 
          && scriptLoader.loadResource().validate()
          && null != handler) {
      return true;
    }
    else {
      return false;
    }
  }
  
  private void addParameters(ScriptEngine engine, Map<String, Object> paraMap) {
    if(null != paraMap) {
      Iterator<Entry<String, Object>> it = paraMap.entrySet().iterator();
      while( it.hasNext() ) {
        Entry<String, Object> entry = it.next();
        engine.put(entry.getKey(), entry.getValue());
      }
    }
  }
  
  public void execute(Map<String, Object> paraMap, ResultHandler handler) throws ScriptException, IOException {
    if(!validateEnv(handler)) return;
    try {
      ScriptEngineManager manager = new ScriptEngineManager();
      ScriptEngine engine = manager.getEngineByName(scriptLoader.loadResource().getScriptType().value());
      if(null == engine) return;
      if(null != handler.getWriter()) {
        ScriptContext context = engine.getContext();
        context.setWriter(handler.getWriter());
        context.setErrorWriter(handler.getErrorWriter());
      }
      addParameters(engine, paraMap);
      engine.eval(scriptLoader.loadResource().getScriptReader());
      List<String> params = handler.getParameterNames();
      if(null != params && params.size() > 0) {
        Map<String, Object> handleMap= new HashMap<String, Object>();
        for(String paraName: params) {
          handleMap.put(paraName, engine.get(paraName));
        }
        handler.handleResult(handleMap);
      }
    } catch (ScriptException e){
      throw e;
    }
    finally {
      scriptLoader.loadResource().getScriptReader().close();
    }
  }

  public ScriptLoader getScriptLoader() {
    return scriptLoader;
  }

  public void setScriptLoader(ScriptLoader scriptLoader) {
    this.scriptLoader = scriptLoader;
  }
  
}
