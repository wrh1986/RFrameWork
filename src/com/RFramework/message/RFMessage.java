package com.RFramework.message;

import flexjson.JSONSerializer;

public class RFMessage {

  public String toString() {
    JSONSerializer serializer = new JSONSerializer();
    return serializer.serialize(this);
  }
}
