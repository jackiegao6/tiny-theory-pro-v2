package com.gzc.infrastructure.adapter.repository;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.gzc.domain.login.adapter.repository.ILoginRepository;
import com.gzc.domain.login.model.entity.LoginInfoEntity;
import com.gzc.infrastructure.dao.IUserDao;
import com.gzc.infrastructure.dao.po.User;
import com.gzc.infrastructure.redis.IRedisService;
import com.gzc.types.common.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.gzc.types.common.Constants.LOGIN_KEY;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LoginRepository implements ILoginRepository {

    private final IUserDao userDao;
    private final IRedisService redisService;

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

    @Override
    public void saveCode2Redis(String phone, String code) {
        redisService.setValue( LOGIN_KEY + phone, code, 120000);// 2 min
    }

    @Override
    public boolean isEqualCode(String phone, String code) {
        String cacheCode = redisService.getValue(LOGIN_KEY + phone);
        return cacheCode.equals(code);
    }

    @Override
    public void saveUser2Redis(String phone) {
        String token = UUID.randomUUID().toString(true);
        redisService.setValue(Constants.USER_TOKEN + token, phone, 1800000); // 1 h
    }
}
