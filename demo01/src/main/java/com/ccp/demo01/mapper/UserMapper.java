package com.ccp.demo01.mapper;

import com.ccp.demo01.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * {崔纯鹏}
 * 2019/12/9 21:00
 * Version:1.0
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified) " +
            "values (#{name},#{accountID},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

}
