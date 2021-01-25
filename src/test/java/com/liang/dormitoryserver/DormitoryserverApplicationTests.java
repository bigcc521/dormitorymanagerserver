package com.liang.dormitoryserver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import com.liang.dormitoryserver.entity.District;
import com.liang.dormitoryserver.mapper.DistrictMapper;
import com.liang.dormitoryserver.mapper.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class DormitoryserverApplicationTests {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    BCryptPasswordEncoder PasswordEncoder;
    @Autowired
    DistrictMapper districtMapper;
    @Test
    void contextLoads() {
        System.out.println(PasswordEncoder.encode("123456"));
    }
    @Test
    void test1(){
        List<District> districts = districtMapper.getDistrictWithApartment();
        FastJsonRedisSerializer serializer=new FastJsonRedisSerializer<District>(District.class);
        byte[] temp = JSON.toJSONString(districts).getBytes();
        List<District> objects = JSON.parseArray(new String(temp),District.class);
        System.out.println(objects);
    }

}
