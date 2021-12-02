package com.taskify.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskifyTaskManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskifyTaskManagementServiceApplication.class, args);
	}

}
