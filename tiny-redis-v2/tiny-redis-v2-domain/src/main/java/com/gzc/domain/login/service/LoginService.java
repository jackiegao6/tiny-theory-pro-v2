package com.gzc.domain.login.service;


import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import com.gzc.domain.login.adapter.repository.ILoginRepository;
import com.gzc.domain.login.model.entity.LoginInfoEntity;
import com.gzc.types.enums.ResponseCode;
import com.gzc.types.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService implements ILoginService{

    private final ILoginRepository loginRepository;

    @Override
    public String sendCode(String phone) {

        // 1. 校验手机号
        boolean isMobile = Validator.isMobile(phone);
        if (!isMobile){
            log.info("手机号格式错误");
            throw new AppException(ResponseCode.ILLEGAL_PHONE.getCode(), ResponseCode.ILLEGAL_PHONE.getInfo());
        }
        // 2. 生成验证码
        String code = RandomUtil.randomNumbers(4);
        // 3. 模拟发送验证码
        log.info("发送验证码成功 phone: {} code: {}", phone, code);

        return code;
    }

    @Override
    public void login(LoginInfoEntity loginInfoEntity) {

        // 1. 校验手机号
        String phone = loginInfoEntity.getPhone();
        boolean isMobile = Validator.isMobile(phone);
        if (!isMobile){
            log.info("手机号格式错误");
            throw new AppException(ResponseCode.ILLEGAL_PHONE.getCode(), ResponseCode.ILLEGAL_PHONE.getInfo());
        }

        // 2. 校验验证码
        String code = loginInfoEntity.getCode();
        boolean isCode = true;
        if (!isCode){
            log.info("验证码错误");
            throw new AppException(ResponseCode.ILLEGAL_CODE.getCode(), ResponseCode.ILLEGAL_CODE.getInfo());
        }

        // 3. 查询用户是否存在
        LoginInfoEntity loginInfoEntityResp = loginRepository.existUser(phone);
        if (null != loginInfoEntityResp){
            // 3.1 用户存在
            String password = loginInfoEntity.getPassword();
            if (null != password && !password.equals(loginInfoEntityResp.getPassword())){
                throw new AppException(ResponseCode.WRONG_PASSWORD.getCode(), ResponseCode.WRONG_PASSWORD.getInfo());
            }
        }
        // 3.2 用户不存在
        loginRepository.createUser(phone);
    }
}
