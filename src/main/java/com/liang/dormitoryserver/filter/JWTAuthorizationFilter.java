package com.liang.dormitoryserver.filter;

import com.alibaba.fastjson.JSON;
import com.liang.dormitoryserver.config.GenericResponse;
import com.liang.dormitoryserver.config.ServiceError;
import com.liang.dormitoryserver.util.JwtTokenUtil;
import com.liang.dormitoryserver.util.RedisUtil;
import com.liang.dormitoryserver.util.SpringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @ClassName JWTAuthorizationFilter
 * @Description TODO 在UsernamePasswordAuthenticationFilter之前检查是否有token，如果有则生成UsernamePasswordAuthenticationToken
 * @Author Liang Xi
 * @DATE 2021/1/16 15:29
 * @Version 1.0
 */

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    RedisUtil redisUtil=SpringUtils.getBean(RedisUtil.class);
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(JwtTokenUtil.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        String token = tokenHeader.replace(JwtTokenUtil.TOKEN_PREFIX, "");
        String username = null;
        String role = null;
        try {
            username = JwtTokenUtil.getUsername(token);
            role = JwtTokenUtil.getUserRole(token);
            if(redisUtil.hasKey(username)){//如果这个redis中存在这个用户
                String tokenvalue= (String) redisUtil.get(username);
                if(tokenvalue.equals(token)){//判断这个用户的token是否和传过来的token一样----》单点登录
                    SecurityContextHolder.getContext().setAuthentication(getAuthentication(username,role));
                    redisUtil.set(username,token);//将用户名和token重新写入redis来达到续签
                }else {
                    //该用户在redis中的token和传过来的token不一样，该token已失效（可能用户已重新登录过或在别的地方登录）
                    response.getWriter().write(JSON.toJSONString(GenericResponse.response(ServiceError.GLOBAL_ERR_OTHER_SIGN_IN)));
                }
            }else{
                //redis中不存在这个用户token无用
                response.getWriter().write(JSON.toJSONString(GenericResponse.response(ServiceError.GLOBAL_ERR_NO_SIGN_IN)));
            }
        } catch (Exception e) {
            //我设置了jwt七天过期，也就是说就算七天都保持一个小时内刷新一次，也要重新登录
            response.getWriter().write(JSON.toJSONString(GenericResponse.response(ServiceError.GLOBAL_ERR_NO_SIGN_IN)));
        }
        super.doFilterInternal(request, response, chain);
    }
    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String username,String role) {
        if (username != null){
            System.out.println("token---->username:"+username);
            if(role.equals("")){
                System.out.println("没有任何角色");
                return new UsernamePasswordAuthenticationToken(username, null,AuthorityUtils.NO_AUTHORITIES);
            }else {
                System.out.println("有角色"+role);
                return new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));
            }
        }
        return null;
    }


}
