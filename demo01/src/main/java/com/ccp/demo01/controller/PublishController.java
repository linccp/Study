package com.ccp.demo01.controller;

import com.ccp.demo01.mapper.QuestionMapper;
import com.ccp.demo01.mapper.UserMapper;
import com.ccp.demo01.model.Question;
import com.ccp.demo01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * {崔纯鹏}
 * 2019/12/11 10:27
 * Version:1.0
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String  title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if(title.equals("")){
            System.out.println(true);
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(description.equals("")){
            model.addAttribute("error","问题描述不能为空");
            return "publish";
        }
        if(tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }



//        获取用户名
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length !=0)
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
//
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setCreator(user.getId());
        question.setTag(tag);

        questionMapper.create(question);

        return "redirect:/";
    }

}
