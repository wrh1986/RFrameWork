package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;
import com.RFramework.systemManagement.model.Item;

public abstract class AbstractItem implements Item {

  public abstract Auth getAuth();
}
