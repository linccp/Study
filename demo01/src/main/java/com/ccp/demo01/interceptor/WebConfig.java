package com.ccp.demo01.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * {崔纯鹏}
 * 2019/12/14 10:11
 * Version:1.0
 */
@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInerceptor sessionInerceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(sessionInerceptor).addPathPatterns("/**");
    }


}
