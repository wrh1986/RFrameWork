package com.RFramework.validation;

public class ValidationPath {
  private ValidationNode fromNode;
  private ValidationNode toNode;
  private ValidationCondition condition;
  
  public ValidationPath(ValidationNode fromNode, ValidationNode toNode, ValidationCondition condition) {
    this.fromNode = fromNode;
    this.toNode = toNode;
    this.condition = condition;
  }
  public ValidationNode getFromNode() {
    return fromNode;
  }
  public void setFromNode(ValidationNode fromNode) {
    this.fromNode = fromNode;
  }
  public ValidationNode getToNode() {
    return toNode;
  }
  public void setToNode(ValidationNode toNode) {
    this.toNode = toNode;
  }
  public ValidationCondition getCondition() {
    return condition;
  }
  public void setCondition(ValidationCondition condition) {
    this.condition = condition;
  }
}
