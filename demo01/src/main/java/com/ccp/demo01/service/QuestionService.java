package com.ccp.demo01.service;

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
 * 2019/12/12 15:25
 * Version:1.0
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> list() {
        List<Question> questions = questionMapper.list();
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
        return questionDtoList;
    }
}
