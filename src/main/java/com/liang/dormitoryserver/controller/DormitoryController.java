package com.liang.dormitoryserver.controller;


import com.liang.dormitoryserver.config.GenericResponse;
import com.liang.dormitoryserver.config.ServiceError;
import com.liang.dormitoryserver.entity.Dormitory;
import com.liang.dormitoryserver.service.DormitoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
@RestController
@RequestMapping("/dormitoryserver/dormitory")
public class DormitoryController {
    @Autowired
    DormitoryService dormitoryService;
    @Secured(value = {"ROLE_inspector"})
    @GetMapping("/{apartment}")
    public GenericResponse getDormitory(@PathVariable("apartment") String apartment){
        Collection<Dormitory> dormitories = dormitoryService.listByApartmentId(Integer.valueOf(apartment));
        return GenericResponse.response(ServiceError.NORMAL,dormitories);
    }
}
