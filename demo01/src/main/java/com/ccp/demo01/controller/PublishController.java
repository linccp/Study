package com.ccp.demo01.controller;

import com.ccp.demo01.Dto.QuestionDto;
import com.ccp.demo01.mapper.QuestionMapper;
import com.ccp.demo01.model.Question;
import com.ccp.demo01.model.User;
import com.ccp.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * {崔纯鹏}
 * 2019/12/11 10:27
 * Version:1.0
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        QuestionDto question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }

    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String  title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "id",required = false) Integer id,
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

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
//
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setTag(tag);
        question.setId(id);
        questionService.creatOrUpdate(question);
        return "redirect:/";
    }

}
