package com.zhizhi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Yu Yang
 * @create 2020-06-13 16:16
 */
// @Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
