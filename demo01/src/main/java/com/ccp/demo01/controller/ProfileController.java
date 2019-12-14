package com.ccp.demo01.controller;

import com.ccp.demo01.Dto.ProfileDto;
import com.ccp.demo01.model.User;
import com.ccp.demo01.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * {崔纯鹏}
 * 2019/12/13 16:09
 * Version:1.0
 */
@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          HttpServletRequest request, Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "3") Integer size) {

        User user = (User)request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        ProfileDto profileDto = profileService.list(user.getId(),page,size);
        model.addAttribute("profile",profileDto);
        return "profile";
    }
}
