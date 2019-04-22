package com.hawcore.learning.spiderapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.hawcore.learning.spiderapp.*.mapper"})
public class SpiderAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpiderAppApplication.class, args);
    }

}
