package com.taskify.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskifyAccountManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskifyAccountManagementServiceApplication.class, args);
	}

}
