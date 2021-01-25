package com.liang.dormitoryserver.controller;


import com.alibaba.fastjson.JSON;
import com.liang.dormitoryserver.config.GenericResponse;
import com.liang.dormitoryserver.config.ServiceError;
import com.liang.dormitoryserver.entity.Tgrade;
import com.liang.dormitoryserver.service.TgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lq
 * @since 2021-01-14
 */
@RestController
@RequestMapping("/dormitoryserver/tgrade")
public class TgradeController {
    @Autowired
    TgradeService tgradeService;
    @Value("${healthscorephoto}")
    private String healthscorephoto;
    @Secured(value = {"ROLE_inspector"})
    @PostMapping
    public GenericResponse saveGrade(@RequestBody Tgrade tgrade){
        tgrade.setScoringdate(LocalDate.now());
        tgradeService.save(tgrade);
        System.out.println("tgrade"+tgrade);
        return GenericResponse.response(ServiceError.NORMAL);
    }

}
