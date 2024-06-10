package com.docmall.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.docmall.demo.mapper"})
@SpringBootApplication
public class Ex08Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex08Application.class, args);
	}

}
