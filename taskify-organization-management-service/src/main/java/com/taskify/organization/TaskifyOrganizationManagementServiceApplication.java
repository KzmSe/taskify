package com.taskify.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TaskifyOrganizationManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskifyOrganizationManagementServiceApplication.class, args);
	}

}
