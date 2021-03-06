package com.zhizhi.config;

import com.zhizhi.wrapper.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.PrintWriter;

/**
 * Web Security配置类
 *
 * @author Yu Yang
 * @create 2020-06-13 16:16
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 登录认证逻辑：在未登录情况下访问需要认证的资源时会触发authenticationEntryPoint，
     * 之后向客户端返回json消息表示用户未登录，提醒客户端跳转登录页面"/login.html"，并以"username"和"password"字段来POST
     * 登录认证表单到"/login"，认证通过即可放行并返回给客户端登陆成功的json消息，认证不通过则不放行并返回
     * 给客户端登录失败的json消息。
     *
     * 用户注销逻辑：客户端发送GET请求到"/logout"，服务端返回注销成功的json消息。
     *
     * 前后端分离开发阶段：后端允许跨域请求，并在各个处理器Handler中的响应httpServletResponse中添加响应头来支持跨域。
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .configurationSource(getCorsConfigurationSource()) // 允许跨域请求
                .and()
                .authorizeRequests()
                .antMatchers("/user/new", "/question/get/all")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling() // 未登录时的处理
                .authenticationEntryPoint((httpServletRequest, httpServletResponse, authenticationException) -> { // 未登录返回json
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    httpServletResponse.setCharacterEncoding("utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.write(new ResponseObject("failure", "用户尚未登录！").toString());
                    printWriter.flush();
                    printWriter.close();
                })
                .and()
                .formLogin()
                .loginProcessingUrl("/login") // 登录接口：用户登录时表单POST的URL
                .usernameParameter("username") // 定义表单提交时的字段：username, password
                .passwordParameter("password")
                .permitAll()
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> { // 登录认证成功返回json
                    System.out.println("User " + httpServletRequest.getParameter("username") + ", welcome!");
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    httpServletResponse.setCharacterEncoding("utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.write(new ResponseObject("success", "登录成功！").toString());
                    printWriter.flush();
                    printWriter.close();
                })
                .failureHandler((httpServletRequest, httpServletResponse, authenticationException) -> { // 登录认证失败返回json
                    System.out.println("Login error!");
                    System.out.println("username : " + httpServletRequest.getParameter("username") + ", password : "
                                        + httpServletRequest.getParameter("password"));
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    httpServletResponse.setCharacterEncoding("utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.write(new ResponseObject("failure", "登录失败！").toString());
                    printWriter.flush();
                    printWriter.close();
                })
                .and()
                .logout()
                .logoutUrl("/logout") // 客户端向"/logout"发送GET请求即可注销登录
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> { // 用户注销成功返回json
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    httpServletResponse.setCharacterEncoding("utf-8");
                    PrintWriter printWriter = httpServletResponse.getWriter();
                    printWriter.write(new ResponseObject("success", "注销成功！").toString());
                    printWriter.flush();
                    printWriter.close();
                })
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/login.html", "/static/**"); // 不拦截静态资源、登录页面和index页面
    }

    /**
     * 配置跨域源
     * @return
     */
    private CorsConfigurationSource getCorsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true); // 记住登录，每次请求时携带验证信息
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}
