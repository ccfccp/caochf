package com.example.demom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demom.mapper")
public class DemomApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemomApplication.class, args);
    }

}
