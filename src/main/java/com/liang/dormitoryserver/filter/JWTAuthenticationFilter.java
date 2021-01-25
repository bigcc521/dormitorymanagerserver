package com.liang.dormitoryserver.filter;

import com.alibaba.fastjson.JSON;
import com.liang.dormitoryserver.config.GenericResponse;
import com.liang.dormitoryserver.config.ServiceError;
import com.liang.dormitoryserver.entity.Teacher;
import com.liang.dormitoryserver.util.JwtTokenUtil;
import com.liang.dormitoryserver.util.RedisUtil;
import com.liang.dormitoryserver.util.SpringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @ClassName JWTAuthenticationFilter
 * @Description TODO 重写UsernamePasswordAuthenticationFilter，使成功后发送token和jason数据
 * @Author Liang Xi
 * @DATE 2021/1/16 15:12
 * @Version 1.0
 */

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    RedisUtil redisUtil=SpringUtils.getBean(RedisUtil.class);
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = obtainUsername(request);
        username = (username != null) ? username : "";
        username = username.trim();
        String password = obtainPassword(request);
        password = (password != null) ? password : "";
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password, AuthorityUtils.NO_AUTHORITIES)
        );

    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        Teacher teacher = (Teacher) authResult.getPrincipal();
        Collection<? extends GrantedAuthority> authorities = teacher.getAuthorities();
        String role = "";
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        String token = JwtTokenUtil.createToken(teacher.getUsername(), role,true);
        redisUtil.set(teacher.getUsername(),token);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        request.setCharacterEncoding("utf-8");
        response.setHeader(JwtTokenUtil.TOKEN_HEADER, JwtTokenUtil.TOKEN_PREFIX + token);
        response.getWriter().write(JSON.toJSONString(GenericResponse.response(ServiceError.NORMAL,teacher)));
    }

    // 这是验证失败时候调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(GenericResponse.response(ServiceError.GLOBAL_ERR_NO_CODE)));
    }
}
