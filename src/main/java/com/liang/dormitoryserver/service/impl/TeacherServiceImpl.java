package com.liang.dormitoryserver.service.impl;

import com.liang.dormitoryserver.entity.Teacher;
import com.liang.dormitoryserver.mapper.TeacherMapper;
import com.liang.dormitoryserver.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    @Cacheable(cacheNames = "teacher",key = "#root.args[0]+'role'",unless="#result == null")
    public Teacher getTeacherWithRole(String name) {
        return baseMapper.getTeacherWithRole(name);
    }
}
