package com.zhizhi.controller;

import com.zhizhi.model.Question;
import com.zhizhi.model.ResponseObject;
import com.zhizhi.model.User;
import com.zhizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 处理user相关请求的控制器
 *
 * @author Yu Yang
 * @create 2020-06-13 17:15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 处理用户注册
     * @param user 用户信息
     * @return json对象ResponseObject
     */
    @PostMapping("/new")
    public ResponseObject reg(User user) {
        int result = userService.regUser(user);
        if (result == 0) {
            return new ResponseObject("failure", "当前用户名已存在！");
        } else if (result == 1) {
            return new ResponseObject("success", "注册成功");
        } else {
            return new ResponseObject("failure", "服务器错误，注册失败！");
        }
    }

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户User对象的序列化结果
     */
    @GetMapping("/get")
    public User getUser(String username) {
        User user = (User) userService.loadUserByUsername(username);
        return user;
    }
}
