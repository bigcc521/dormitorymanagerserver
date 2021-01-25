package com.liang.dormitoryserver.mapper;

import com.liang.dormitoryserver.entity.District;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
public interface DistrictMapper extends BaseMapper<District> {
    List<District> getDistrictWithApartment();
}
