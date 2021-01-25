package com.liang.dormitoryserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liang.dormitoryserver.entity.Dormitory;
import com.liang.dormitoryserver.mapper.DormitoryMapper;
import com.liang.dormitoryserver.service.DormitoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DormitoryServiceImpl extends ServiceImpl<DormitoryMapper, Dormitory> implements DormitoryService {
    @Autowired
    DormitoryMapper dormitoryMapper;
    @Cacheable(cacheNames = {"dormitorylist"},key = "#root.args[0]+'listbyapartment'",unless="#result == null")
    @Override
    public List<Dormitory> listByApartmentId(Integer apartmentId) {
        QueryWrapper<Dormitory> wrapper = new QueryWrapper<>();
        wrapper.eq("apartment",apartmentId);
        List<Dormitory> dormitories = dormitoryMapper.selectList(wrapper);
        return dormitories;
    }
}
