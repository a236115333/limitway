package com.limitway.current;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.limitway.current.mapper"})
public class LimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimitApplication.class, args);
	}

}
