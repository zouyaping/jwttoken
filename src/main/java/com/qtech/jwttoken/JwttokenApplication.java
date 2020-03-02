package com.qtech.jwttoken;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qtech.jwttoken.mapper")
public class JwttokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwttokenApplication.class, args);
    }

}
