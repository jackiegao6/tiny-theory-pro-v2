package com.gzc.domain.login.service;

import com.gzc.domain.login.model.entity.LoginInfoEntity;

public interface ILoginService {

    String sendCode(String phone);

    void login(LoginInfoEntity loginInfoEntity);

}
