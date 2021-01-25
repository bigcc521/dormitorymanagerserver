package com.liang.dormitoryserver.service.impl;

import com.liang.dormitoryserver.entity.Topic;
import com.liang.dormitoryserver.mapper.TopicMapper;
import com.liang.dormitoryserver.service.TopicService;
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
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {

}
