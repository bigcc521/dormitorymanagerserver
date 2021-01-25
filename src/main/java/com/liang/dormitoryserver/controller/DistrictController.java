package com.liang.dormitoryserver.controller;


import com.liang.dormitoryserver.config.GenericResponse;
import com.liang.dormitoryserver.config.ServiceError;
import com.liang.dormitoryserver.entity.District;
import com.liang.dormitoryserver.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
@RestController
@RequestMapping("/dormitoryserver/district")
public class DistrictController {
    @Autowired
    DistrictService districtService;
    @Secured(value = {"ROLE_inspector"})
    @GetMapping
    public GenericResponse getDistrictWithApartment(){
        List<District> districts = districtService.getDistrictWithApartment();
        return GenericResponse.response(ServiceError.NORMAL,districts);
    }
}
