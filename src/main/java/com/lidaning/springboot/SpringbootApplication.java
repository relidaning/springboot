package com.lidaning.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//@EntityScan(basePackages ="com.lidaning.springboot")
@SpringBootApplication
@MapperScan("com.lidaning.springboot.glance")
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}
