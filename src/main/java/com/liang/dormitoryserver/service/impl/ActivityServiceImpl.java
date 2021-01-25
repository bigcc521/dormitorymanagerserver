package com.liang.dormitoryserver.service.impl;

import com.liang.dormitoryserver.entity.Activity;
import com.liang.dormitoryserver.mapper.ActivityMapper;
import com.liang.dormitoryserver.service.ActivityService;
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
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

}
