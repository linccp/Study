package com.ccp.demo01.controller;

import com.ccp.demo01.Dto.QuestionDto;
import com.ccp.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * {崔纯鹏}
 * 2019/12/14 12:19
 * Version:1.0
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")  Integer id,
                           Model model){
        QuestionDto questionDto = questionService.getById(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
