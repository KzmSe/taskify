package com.taskify.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskifyNotificationManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskifyNotificationManagementServiceApplication.class, args);
	}

}
