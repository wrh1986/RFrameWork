package com.RFramework.validation;

import java.util.List;

public class ValidationHandler {

  private static ValidationHandler handler;
  private ValidationHandler(){}
  public ValidationHandler getInstance(){
    if(null == handler) {
      synchronized(this) {
        handler = new ValidationHandler();
        initialize();
      }
    }
    return handler;
  }
  
  private void initialize() {
    
  }
  
  public boolean validate(ValidationNode fromNode, ValidationNode toNode, Object... params){
    boolean result = false;
    List<ValidationPath[]> pathList = null;//ValidationPathMap.getPathbyFromNode(fromNode);
    for(ValidationPath[] paths : pathList) {
      Boolean innerResult = null;
      for(ValidationPath path : paths) {
        if(null == innerResult) innerResult = true;
        innerResult = path.getCondition().validate(params) && innerResult;
      }
      if(null != innerResult) {
        if(innerResult){
          result = true;
          break;
        }
      }
    }
    return result;
  }
}
