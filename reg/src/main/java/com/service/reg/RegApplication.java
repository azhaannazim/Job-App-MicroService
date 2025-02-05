package com.service.reg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegApplication.class, args);
	}

}
