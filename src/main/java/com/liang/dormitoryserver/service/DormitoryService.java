package com.liang.dormitoryserver.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.liang.dormitoryserver.entity.Dormitory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
public interface DormitoryService extends IService<Dormitory> {
    List<Dormitory> listByApartmentId(Integer id);
}
