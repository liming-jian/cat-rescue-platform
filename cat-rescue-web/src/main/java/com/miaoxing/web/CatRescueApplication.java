package com.miaoxing.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = "com.miaoxing")
@MapperScan("com.miaoxing.business.mapper")
@ConfigurationPropertiesScan
public class CatRescueApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatRescueApplication.class, args);
    }
}
