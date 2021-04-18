package com.springcloud.limitservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationProperties("")
public class LimitServicesApplication {

	public static void main(String[] args) {

		SpringApplication.run(LimitServicesApplication.class, args);
	}

}
