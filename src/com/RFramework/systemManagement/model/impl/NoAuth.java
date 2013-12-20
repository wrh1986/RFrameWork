package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;

public class NoAuth extends AbstractAuth {
      @Override
      public boolean isAuthorized() {
        // TODO Auto-generated method stub
        return false;
      }

      public boolean gt (Auth a) {
          return true;
      }

    public  boolean lt (Auth a) {
        return false;
    }
}
