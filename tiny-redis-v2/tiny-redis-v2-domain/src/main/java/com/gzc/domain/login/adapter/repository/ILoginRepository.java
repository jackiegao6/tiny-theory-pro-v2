package com.gzc.domain.login.adapter.repository;

import com.gzc.domain.login.model.entity.LoginInfoEntity;

public interface ILoginRepository {

    LoginInfoEntity existUser(String phone);

    void createUser(String phone);

    void saveCode2Redis(String phone, String code);

    boolean isEqualCode(String phone, String code);

    void saveUser2Redis(String phone);


}
