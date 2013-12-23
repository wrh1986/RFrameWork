package com.RFramework.systemManagement.model;

import com.RFramework.systemManagement.model.impl.NoAuth;
import com.RFramework.systemManagement.model.impl.ReadAuth;
import com.RFramework.systemManagement.model.impl.WriteAuth;

public interface Auth{
  
  Auth NOAUTH = new NoAuth();
  Auth ROAUTH = new ReadAuth();
  Auth RWAUTH = new WriteAuth();

  public boolean gt (Auth a);
  public boolean lt (Auth a);
}
