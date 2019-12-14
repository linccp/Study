package com.ccp.demo01.service;


import com.ccp.demo01.Dto.ProfileDto;
import com.ccp.demo01.Dto.QuestionDto;
import com.ccp.demo01.mapper.QuestionMapper;
import com.ccp.demo01.mapper.UserMapper;
import com.ccp.demo01.model.Question;
import com.ccp.demo01.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * {崔纯鹏}
 * 2019/12/13 20:18
 * Version:1.0
 */
@Service
public class ProfileService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;


    public ProfileDto list(Integer userId, Integer page, Integer size) {
        ProfileDto profileDto = new ProfileDto();
        Integer profilecount = questionMapper.countByUserId(userId);
        profileDto.setProfile(profilecount,page,size);

        //        判断分页超限
        if(page <1){
            page = 1;
        }else if(page > profileDto.getProfilepage()){
            page = profileDto.getProfilepage();
        }

//        分页计算
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for(Question question:questions){
            User user = null;
            user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
//            把question对象属性拷贝至questionDto
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        profileDto.setProfiles(questionDtoList);

        return profileDto;
    }
}
