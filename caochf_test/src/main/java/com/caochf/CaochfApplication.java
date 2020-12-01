package com.caochf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.caochf.mapper")
@SpringBootApplication
public class CaochfApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaochfApplication.class, args);
    }


}
