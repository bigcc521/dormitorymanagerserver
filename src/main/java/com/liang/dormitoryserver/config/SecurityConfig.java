package com.liang.dormitoryserver.config;

import com.alibaba.fastjson.JSON;
import com.liang.dormitoryserver.filter.JWTAuthenticationFilter;
import com.liang.dormitoryserver.filter.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName SecurityConfig
 * @Description TODO
 * @Author Liang Xi
 * @DATE 2021/1/15 14:51
 * @Version 1.0
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService service;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//关闭session管理，使用token机制处理
//                .and()
//                .exceptionHandling().authenticationEntryPoint(AjaxAuthenticationEntryPoint())//未登陆时返回 JSON 格式的数据给前端
//                .accessDeniedHandler(AjaxAccessDeniedHandler()) //无权访问返回的 JSON 格式数据给前端
//                .and()
//                .formLogin().loginProcessingUrl("/login")
//                .successHandler( AjaxauthenticationSuccessHandler()) // 登录成功
//                .failureHandler( AjaxauthenticationFailureHandler()) // 登录失败
//                .permitAll()
//                .and()
//                .logout()//默认注销行为为logout
//                .logoutUrl("/logout")
//                .logoutSuccessHandler(AjaxlogoutSuccessHandler())
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                ;
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 测试用资源，需要验证了的用户才能访问
//                .antMatchers("/tasks/**").authenticated()
                // 需要角色为ADMIN才能删除该资源
//                .antMatchers("/tasks/**").hasRole("manager")
                // 其他都放行了
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(AjaxAuthenticationEntryPoint())//未登陆时返回 JSON 格式的数据给前端
                .accessDeniedHandler(AjaxAccessDeniedHandler());//无权访问返回的 JSON 格式数据给前端
    }

    //未登陆时返回 JSON 格式的数据给前端（否则为 html）
    @Bean
    public AuthenticationEntryPoint AjaxAuthenticationEntryPoint(){
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                System.out.println("你没有传入jwt令牌");
            }
        };
    }
    //无权访问返回的 JSON 格式数据给前端（否则为 403 html 页面）
    @Bean
    public AccessDeniedHandler AjaxAccessDeniedHandler(){
        return new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.setCharacterEncoding("utf-8");
                response.getWriter().write(JSON.toJSONString(GenericResponse.response(ServiceError.GLOBAL_ERR_NO_AUTHORITY)));
            }
        };
    }

}
