package com.RFramework.systemManagement.model.impl;

import com.RFramework.systemManagement.model.Auth;

import java.util.List;

/**
 * Created by wangronghua on 13-12-19.
 */
public class AuthHelper {

    public static Auth getMaxAuth(List<Auth> authList) {
        Auth result = Auth.NOAUTH;
        for (Auth a: authList) {
           if(a.gt(result)) {
               result = a;
           }
        }
        return result;
    }

    public static Auth getMinAuth(List<Auth> authList) {
        Auth result = Auth.NOAUTH;
        for (Auth a: authList) {
            if (a.lt(result)) {
                result = a;
            }
        }
        return result;
    }
}
