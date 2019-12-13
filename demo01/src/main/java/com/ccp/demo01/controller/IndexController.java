package com.ccp.demo01.controller;

import com.ccp.demo01.Dto.PaginationDto;
import com.ccp.demo01.Dto.QuestionDto;
import com.ccp.demo01.mapper.QuestionMapper;
import com.ccp.demo01.mapper.UserMapper;
import com.ccp.demo01.model.Question;
import com.ccp.demo01.model.User;
import com.ccp.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * {崔纯鹏}
 * 2019/12/8 15:37
 * Version:1.0
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;


    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "3") Integer size){

        System.out.println(page + "\t" + size);
//        查看网页时，找到所有的cookies
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

        PaginationDto paginationDto = questionService.list(page,size);
        model.addAttribute("pagination",paginationDto);
        return "index";
    }
}
