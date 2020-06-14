package com.zhizhi.service;

import com.zhizhi.mapper.UserMapper;
import com.zhizhi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User服务类。同时实现了Spring Security中的UserDetailsService，用于用户身份验证。
 *
 * @author Yu Yang
 * @create 2020-06-13 16:22
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据用户名查用户
     * @param s 用户名
     * @return UserDetails对象
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUsername(s);
        if (user == null) { // 若查询为null，返回一个空User，后续的密码匹配自动失败
            return new User();
        }
        return user;
    }

    /**
     * 注册用户
     * @param user User体
     * @return 0表示用户名已存在；1表示注册成功；2表示注册失败
     */
    public int regUser(User user) {
        User userFromDB = userMapper.selectUserByUsername(user.getUsername());
        if (userFromDB != null) {
            return 0;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // 加密密码
        user.setRegTime(dateFormat.format(new Date())); // 添加用户注册时间
        int userId = userMapper.insertUser(user);
        if (userId > 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * 根据用户名查用户id
     * @param username 用户名
     * @return 用户id
     */
    public int selectIdByUsername(String username) {
        User user = userMapper.selectUserByUsername(username);
        if (user == null) {
            return 0;
        } else {
            return user.getId();
        }
    }
}
