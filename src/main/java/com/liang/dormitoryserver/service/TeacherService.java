package com.liang.dormitoryserver.service;

import com.liang.dormitoryserver.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
public interface TeacherService extends IService<Teacher> {
    public Teacher getTeacherWithRole(String name);

}
