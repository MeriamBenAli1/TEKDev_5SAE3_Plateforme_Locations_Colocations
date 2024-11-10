package com.example.msblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MsBlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsBlogApplication.class, args);
	}

}
