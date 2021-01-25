package com.liang.dormitoryserver.config;

import com.liang.dormitoryserver.entity.Teacher;
import com.liang.dormitoryserver.mapper.TeacherMapper;
import com.liang.dormitoryserver.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @ClassName SelfUserDetailsService
 * @Description TODO
 * @Author Liang Xi
 * @DATE 2021/1/15 15:24
 * @Version 1.0
 */
@Service
public class SelfUserDetailsService implements UserDetailsService {
    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    TeacherService teacherService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = teacherService.getTeacherWithRole(username);
        if(teacher == null){
            //仍需要细化处理
            throw new UsernameNotFoundException("该用户不存在");
        }

        return teacher;
    }
}
