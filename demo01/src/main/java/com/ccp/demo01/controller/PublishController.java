package com.ccp.demo01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * {崔纯鹏}
 * 2019/12/11 10:27
 * Version:1.0
 */
@Controller
public class PublishController {

    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }
}
