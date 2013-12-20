package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;

public class ReadAuth extends AbstractAuth {

    public boolean gt (Auth a) {
        if (a == Auth.NOAUTH){
            return true;
        }
        return false;

    }

    public boolean lt (Auth a) {
        if (a == Auth.RWAUTH) {
            return true;
        }
        return false;
    }
}
