package com.zhizhi.controller;

import com.zhizhi.model.ResponseObject;
import com.zhizhi.model.User;
import com.zhizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 处理user相关请求的控制器
 *
 * @author Yu Yang
 * @create 2020-06-13 17:15
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 处理用户注册
     * @param user
     * @return
     */
    @PostMapping("/register")
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
}
