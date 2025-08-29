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
public class RefreshTokenInterceptor implements HandlerInterceptor {

    private final IRedisService redisService;

    /**
     * 针对未被拦截的页面(未登录的用户也可见的页面)
     *                 刷新已登录用户的token有效期
     *                 未登录用户则直接放行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        String phone = redisService.getValue(Constants.USER_TOKEN + token);
        if (StrUtil.isBlank(phone)){
            return true;
        }

        LoginInfoEntity loginInfo = LoginInfoEntity.builder()
                .phone(phone)
                .build();
        UserHolder.setLoginInfoEntity(loginInfo);
        redisService.flushKey(Constants.USER_TOKEN + token, 1800000);// 0.5 h
        return true;
    }
}
