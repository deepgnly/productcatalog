package com.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan
public class Application {

	public static void main(String[] args) {
//		System.getProperties().put("server.port", 8999);

		SpringApplication.run(Application.class, args);

	}

}
