package com.RFramework.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ValidationPathMap {

  private static List<ValidationPath> pathList = new ArrayList<ValidationPath>();
  private static Map<ValidationNode, List<ValidationPath>> fromMap = new HashMap<ValidationNode, List<ValidationPath>>();
  private static Map<ValidationNode, Map<ValidationNode, List<ValidationPath[]>>> pathMap 
                            = new HashMap<ValidationNode, Map<ValidationNode, List<ValidationPath[]>>>();
  public static List<ValidationPath[]> getPathbyNodes(ValidationNode fromNode, ValidationNode toNode) {
    if(null != pathMap.get(fromNode) && null != pathMap.get(fromNode).get(toNode)) {
      return pathMap.get(fromNode).get(toNode);
    }
    List<ValidationPath[]> pathList = new ArrayList<ValidationPath[]>();
    Map<ValidationNode, Boolean> executedMap = new HashMap<ValidationNode, Boolean>();
    Stack<ValidationPath> curStack = new Stack<ValidationPath>();
    getPath(pathList, curStack, fromNode, toNode, executedMap);
    if(null == pathMap.get(fromNode))
    {
      Map<ValidationNode, List<ValidationPath[]>> nodeB = new HashMap<ValidationNode, List<ValidationPath[]>>();
      nodeB.put(toNode, pathList);
      pathMap.put(fromNode, nodeB);
    }
    else if(null == pathMap.get(fromNode).get(toNode)) {
      pathMap.get(fromNode).put(toNode, pathList);
    }
    return pathList;
    //}
    //else {
      //return fromMap.get(fromNode);
    //}
  }

  private static void getPath(List<ValidationPath[]> resultList, Stack<ValidationPath> curStack, ValidationNode fromNode, ValidationNode toNode, Map<ValidationNode, Boolean> executed) {
    if(null != executed.get(fromNode) && executed.get(fromNode)) {
//      System.out.print("Loop:");
//      for(int i = 0; i < curStack.size(); i ++) {
//        System.out.print(curStack.get(i).getFromNode().getName()+"->"+curStack.get(i).getToNode().getName()+"||");
//      }
//      System.out.println();
      return;
    }
    List<ValidationPath> paths = fromMap.get(fromNode);
    if(null != paths){
      executed.put(fromNode, true);
      for(ValidationPath path : paths) {
        curStack.push(path);
        if(path.getToNode().equals(toNode)) {
        	resultList.add(copyStack(curStack));
          curStack.pop();
          return;
        }
        else {
          getPath(resultList, curStack, path.getToNode(), toNode, executed); 
        }
        curStack.pop();
      }
    }
    return;
  }
  
  public static ValidationPath[] copyStack(Stack<ValidationPath> curStack) {
    ValidationPath[] resultArray = new ValidationPath[curStack.size()];
    for(int index = 0; index < curStack.size(); index ++) {
      resultArray[index] = curStack.get(index);
    }
    return resultArray;
  }
  public static void addPath(ValidationPath path) {
    pathList.add(path);
    if( null == fromMap.get(path.getFromNode())) {
      List<ValidationPath> pathList = new ArrayList<ValidationPath>();
      pathList.add(path);
      fromMap.put(path.getFromNode(), pathList);
    }
    else {
      fromMap.get(path.getFromNode()).add(path);
    }
    pathMap.clear();
    //fromMap.clear();
    //toMap.clear();
  }
  
  public static void main(String[] args) {
    ValidationNode a = new ValidationNode("a");
    ValidationNode b = new ValidationNode("b");
    ValidationNode c = new ValidationNode("c");
    ValidationNode d = new ValidationNode("d");
    addPath(new ValidationPath(a,b, null));
    addPath(new ValidationPath(a,c, null));
    addPath(new ValidationPath(a,d, null));
    addPath(new ValidationPath(b,c, null));
    addPath(new ValidationPath(c,b, null));
    addPath(new ValidationPath(b,d, null));
    addPath(new ValidationPath(c,d, null));
    List resultList = getPathbyNodes(a,d);
    
    //System.out.print(resultList.toString());
  }
}
