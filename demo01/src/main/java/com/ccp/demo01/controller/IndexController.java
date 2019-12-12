package com.ccp.demo01.controller;

import com.ccp.demo01.mapper.UserMapper;
import com.ccp.demo01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * {崔纯鹏}
 * 2019/12/8 15:37
 * Version:1.0
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/")
    public String main(HttpServletRequest request){

//        查看网页时，找到所有的cookies
        Cookie[] cookies = request.getCookies();
//        循环遍历cookies找到token
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
        return "index";
    }
}
