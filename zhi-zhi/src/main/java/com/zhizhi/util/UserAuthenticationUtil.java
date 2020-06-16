package com.zhizhi.util;

import com.zhizhi.model.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户认证信息工具类，暂未使用
 *
 * @author Yu Yang
 * @create 2020-06-14 14:41
 */
public class UserAuthenticationUtil {

    /**
     * 获取当前认证者信息，暂未使用
     * @return
     */
    public static User getUserAuthentication() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
