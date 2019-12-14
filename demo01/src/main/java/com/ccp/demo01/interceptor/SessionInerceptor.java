package com.ccp.demo01.interceptor;

import com.ccp.demo01.mapper.UserMapper;
import com.ccp.demo01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * {崔纯鹏}
 * 2019/12/14 10:23
 * Version:1.0
 */
@Service
public class SessionInerceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
//        循环遍历cookies找到token
        if(cookies != null && cookies.length !=0)
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
//                到数据库中查是否存在token这条记录
                    User user = userMapper.findByToken(token);
                    if(user != null){
//                    如果有，则把token放到页面中
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
