package com.gzc.config;

import com.gzc.trigger.http.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        ArrayList<String> excludeUrlList = new ArrayList<>();
        excludeUrlList.add("/api/v1/hmdp/login/send-code");
        excludeUrlList.add( "/api/v1/hmdp/login/login");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrlList);

    }
}
