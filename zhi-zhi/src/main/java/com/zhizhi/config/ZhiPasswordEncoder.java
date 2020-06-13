package com.zhizhi.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 对密码使用加盐加密
 *
 * @author Yu Yang
 * @create 2020-06-13 16:20
 */
@Component
public class ZhiPasswordEncoder extends BCryptPasswordEncoder {
}
