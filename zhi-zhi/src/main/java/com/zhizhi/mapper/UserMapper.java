package com.zhizhi.mapper;

import com.zhizhi.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 注册用户
     * @param record User对象
     * @return 本次新建用户，受影响的行数
     */
    int insertUser(User record);

    /**
     * 根据用户名查用户
     * @param username 用户名
     * @return user对象
     */
    User selectUserByUsername(String username);
}