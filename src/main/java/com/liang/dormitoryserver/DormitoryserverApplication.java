package com.liang.dormitoryserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.liang.dormitoryserver.mapper"})
public class DormitoryserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormitoryserverApplication.class, args);
    }

}
