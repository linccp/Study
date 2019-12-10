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

import javax.servlet.http.HttpServletRequest;
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
                           HttpServletRequest request){
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(clientRedirectUri);
        accessTokenDto.setState(state);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDto);
        GitHubUser githubUser = gitHubProvider.getUser(accessToken);
//        如果登录成功（user不为空），那么开始写cookie 和 session；登录失败则重新登录
        if(githubUser != null){
            User user = new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(githubUser.getName());
//            user.setAccountId(githubUser.getId().toString());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            return "redirect:/";
        }

    }
}
