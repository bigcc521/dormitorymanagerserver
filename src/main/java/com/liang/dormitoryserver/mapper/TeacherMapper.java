package com.liang.dormitoryserver.mapper;

import com.liang.dormitoryserver.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
    public Teacher getTeacherWithRole(String name);
}
