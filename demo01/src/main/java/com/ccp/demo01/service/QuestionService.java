package com.ccp.demo01.service;

import com.ccp.demo01.Dto.PaginationDto;
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

    public PaginationDto list(Integer page, Integer size) {
        PaginationDto paginationDto = new PaginationDto();
        Integer totalpage;
        Integer totalcount = questionMapper.count();
        if(totalcount == 0) {
            totalpage = 1;
        }else if(totalcount % size == 0){
                totalpage = totalcount/size;
        }else{
                totalpage = totalcount/size + 1;
        }


        //        判断分页超限
        if(page <1){
            page = 1;
        }else if(page > totalpage){
            page = totalpage;
        }
        paginationDto.setPagination(totalcount,page,size);
//        分页计算
        Integer offset = size * (page - 1);
        System.out.println(offset + "\t" + size);

        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for(Question question:questions){
//            User user = null;
            User user = userMapper.findById(question.getCreator());
            QuestionDto questionDto = new QuestionDto();
//            把question对象属性拷贝至questionDto
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;
    }


    public QuestionDto getById(Integer id) {
        Question question = questionMapper.getByid(id);
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user = userMapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void creatOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.create(question);
        }else{
            question.setGmtModified(question.getGmtCreate());
            questionMapper.update(question);
        }

    }
}
