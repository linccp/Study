package com.ccp.demo01.controller;

import com.ccp.demo01.Dto.PaginationDto;
import com.ccp.demo01.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * {崔纯鹏}
 * 2019/12/8 15:37
 * Version:1.0
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1") Integer page,
                        @RequestParam(name = "size",defaultValue = "3") Integer size){

        PaginationDto paginationDto = questionService.list(page,size);
        model.addAttribute("pagination",paginationDto);
        return "index";
    }
}
