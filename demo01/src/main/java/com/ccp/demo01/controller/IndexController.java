package com.ccp.demo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {崔纯鹏}
 * 2019/12/8 15:37
 * Version:1.0
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
