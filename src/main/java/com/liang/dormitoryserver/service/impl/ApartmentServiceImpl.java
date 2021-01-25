package com.liang.dormitoryserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.liang.dormitoryserver.entity.Apartment;
import com.liang.dormitoryserver.mapper.ApartmentMapper;
import com.liang.dormitoryserver.service.ApartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
@Service
public class ApartmentServiceImpl extends ServiceImpl<ApartmentMapper, Apartment> implements ApartmentService {


}
