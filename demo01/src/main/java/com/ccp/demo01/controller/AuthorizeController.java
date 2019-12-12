package com.ccp.demo01.controller;

import com.ccp.demo01.Dto.AccessTokenDto;
import com.ccp.demo01.Dto.GitHubUser;
import com.ccp.demo01.mapper.UserMapper;
import com.ccp.demo01.model.User;
import com.ccp.demo01.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * {崔纯鹏}
 * 2019/12/8 21:02
 * Version:1.0
 */
@Controller
public class AuthorizeController {



    @Autowired
    private GitHubProvider gitHubProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String clientRedirectUri;

    @RequestMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(clientRedirectUri);
        accessTokenDto.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser githubUser = gitHubProvider.getUser(accessToken);
//
        if(githubUser != null && githubUser.getId() != null){
            User user = new User();
            String token = UUID.randomUUID().toString();//获取登录信息，得到token
            user.setToken(token);                       //下面吧user信息写到数据库
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatar_url());
            userMapper.insert(user);                    //数据库插入
//            写入cookie
            response.addCookie(new Cookie("token",token));      //token放入cookie
//      如果登录成功（user不为空），那么开始写cookie      request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{                              //登录失败则重新登录
            return "redirect:/";
        }

    }
}
