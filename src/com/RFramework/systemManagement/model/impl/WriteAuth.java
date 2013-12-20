package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;

public class WriteAuth extends ReadAuth {

    public boolean gt (Auth a) {
        return true;

    }

    public boolean lt (Auth a) {
        return false;
    }
}
