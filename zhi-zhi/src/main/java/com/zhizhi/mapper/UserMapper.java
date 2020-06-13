package com.zhizhi.mapper;

import com.zhizhi.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 注册用户
     * @param record
     * @return 用户id
     */
    int insertUser(User record);

    /**
     * 根据用户名查用户
     * @param username
     * @return
     */
    User selectUserByUsername(String username);
}