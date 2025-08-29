package com.gzc.trigger.http.interceptor;

import com.gzc.domain.login.model.entity.LoginInfoEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object loginInfo = session.getAttribute("loginInfo");
        if (null == loginInfo){
            response.setStatus(401);
            return false;
        }
        UserHolder.setLoginInfoEntity((LoginInfoEntity) loginInfo);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // todo ThreadLocal的实现原理
        UserHolder.removeLoginInfoEntity();
    }
}
