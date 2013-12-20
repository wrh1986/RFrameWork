package com.RFramework.utils.script.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.RFramework.utils.script.Script;


public class FileScriptLoader implements ScriptLoader{

  private Reader localReader;
  private Script.Type scriptType;
  public FileScriptLoader(String path, Script.Type scriptType) throws FileNotFoundException {
    this.scriptType = scriptType;
    localReader = new FileReader(path);
  }
  
  public FileScriptLoader(FileReader reader, Script.Type scriptType) {
    this.localReader = reader;
    this.scriptType = scriptType;
  }
  
  @Override
  public Script loadResource() {
    Script result = new Script();
    result.setScriptType(scriptType);
    result.setScriptReader(localReader);
    return result;
  }

}
