package com.liang.dormitoryserver.service.impl;

import com.liang.dormitoryserver.entity.Student;
import com.liang.dormitoryserver.mapper.StudentMapper;
import com.liang.dormitoryserver.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
