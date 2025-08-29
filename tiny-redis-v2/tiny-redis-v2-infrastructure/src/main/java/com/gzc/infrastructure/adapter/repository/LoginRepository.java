package com.gzc.infrastructure.adapter.repository;

import cn.hutool.core.util.RandomUtil;
import com.gzc.domain.login.adapter.repository.ILoginRepository;
import com.gzc.domain.login.model.entity.LoginInfoEntity;
import com.gzc.infrastructure.dao.IUserDao;
import com.gzc.infrastructure.dao.po.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginRepository implements ILoginRepository {

    private final IUserDao userDao;

    @Override
    public LoginInfoEntity existUser(String phone) {

        User userResp = userDao.queryUserByPhone(phone);
        if (null == userResp) return null;
        return LoginInfoEntity.builder()
                .phone(userResp.getPhone())
                .password(userResp.getPassword())
                .build();
    }

    @Override
    public void createUser(String phone) {
        User userReq = new User();
        userReq.setPhone(phone);
        userReq.setNickName("用户" + RandomUtil.randomNumbers(6));
        userDao.createUser(userReq);
    }
}
