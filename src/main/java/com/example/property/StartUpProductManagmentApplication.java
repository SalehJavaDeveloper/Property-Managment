package com.example.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableFeignClients
@SpringBootApplication
@EnableScheduling
public class StartUpProductManagmentApplication  {


	public static void main(String[] args) {
		SpringApplication.run(StartUpProductManagmentApplication.class, args);
	}

}
