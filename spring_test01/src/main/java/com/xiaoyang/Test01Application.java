package com.xiaoyang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xiaoyang.mapper")
public class Test01Application {


    public static void main(String[] args) {
        SpringApplication.run(Test01Application.class, args);
    }
}
