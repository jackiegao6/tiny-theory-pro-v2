package com.gzc.trigger.http.interceptor;

import cn.hutool.core.util.StrUtil;
import com.gzc.domain.login.model.entity.LoginInfoEntity;
import com.gzc.infrastructure.redis.IRedisService;
import com.gzc.types.common.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final IRedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("authorization");
        String phone = redisService.getValue(Constants.USER_TOKEN + token);
        if (StrUtil.isBlank(phone)){
            response.setStatus(401);
            return false;
        }

        LoginInfoEntity loginInfo = LoginInfoEntity.builder()
                .phone(phone)
                .build();
        UserHolder.setLoginInfoEntity(loginInfo);
        redisService.flushKey(Constants.USER_TOKEN + token, 1800000);// 0.5 h
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // todo ThreadLocal的实现原理
        UserHolder.removeLoginInfoEntity();
    }
}
