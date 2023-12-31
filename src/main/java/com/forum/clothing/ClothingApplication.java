package com.forum.clothing;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zb
 */
@SpringBootApplication
@MapperScan
@EnableScheduling
public class ClothingApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClothingApplication.class, args);
	}
}
