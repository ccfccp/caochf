package com.caochf.nature;

//import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.caochf.nature.repository")
public class NatureApplication {

	public static void main(String[] args) {
		SpringApplication.run(NatureApplication.class, args);
	}
}
