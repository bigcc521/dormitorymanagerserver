package com.liang.dormitoryserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.liang.dormitoryserver.entity.District;
import com.liang.dormitoryserver.mapper.DistrictMapper;
import com.liang.dormitoryserver.service.DistrictService;
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
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements DistrictService {
    @Cacheable(cacheNames = "districtlist",key = "'listwithapartment'",unless="#result == null")
    @Override
    public List<District> getDistrictWithApartment() {
        return baseMapper.getDistrictWithApartment();
    }
}
