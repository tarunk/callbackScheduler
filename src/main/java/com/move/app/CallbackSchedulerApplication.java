package com.move.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Starter of the spring boot application
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.move.config", "com.move.scheduled", "com.move.endpoints", "com.move.repositories", "com.move.services"})
@EntityScan(basePackages = {"com.move.entities"})
public class CallbackSchedulerApplication {
	Logger log = LoggerFactory.getLogger(CallbackSchedulerApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CallbackSchedulerApplication.class, args);
	}
}
