package com.gzc.domain.login.service;

import com.gzc.domain.login.model.entity.LoginInfoEntity;

public interface ILoginService {

    String sendCode(String phone);

    Boolean login(LoginInfoEntity loginInfoEntity);

}
