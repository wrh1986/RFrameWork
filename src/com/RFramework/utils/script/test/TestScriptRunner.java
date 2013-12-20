package com.RFramework.utils.script.test;

import java.io.IOException;
import java.io.Reader;

import javax.script.ScriptException;

import com.RFramework.utils.script.Script;
import com.RFramework.utils.script.ScriptRunner;

import com.RFramework.utils.script.handler.DefaultResultHandler;
import com.RFramework.utils.script.handler.ResultHandler;
import com.RFramework.utils.script.loader.DefaultScriptLoader;
import com.RFramework.utils.script.loader.ScriptLoader;

public class TestScriptRunner {

  public static void main(String[] args) throws IOException {
    ScriptLoader loader = new DefaultScriptLoader("import os", Script.Type.PYTHON);
    ResultHandler handler = new DefaultResultHandler();
    ScriptRunner runner = new ScriptRunner();
    runner.setScriptLoader(loader);
    Reader r = loader.loadResource().getScriptReader();
    int k = r.read();
    while(k != -1) {
    	System.out.print((char)k);
    	k = r.read();
    }
    try {
      runner.execute(null, handler);
    } catch (ScriptException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
