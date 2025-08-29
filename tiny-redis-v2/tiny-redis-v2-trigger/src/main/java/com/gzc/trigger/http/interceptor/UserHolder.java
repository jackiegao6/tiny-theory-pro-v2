package com.gzc.trigger.http.interceptor;

import com.gzc.domain.login.model.entity.LoginInfoEntity;

public class UserHolder {

    private static ThreadLocal<LoginInfoEntity> threadLocal = new ThreadLocal<>();

    public static void setLoginInfoEntity(LoginInfoEntity entity){
        threadLocal.set(entity);
    }

    public static LoginInfoEntity getLoginInfoEntity(){
        return threadLocal.get();
    }

    public static void removeLoginInfoEntity(){
        threadLocal.remove();
    }

}
