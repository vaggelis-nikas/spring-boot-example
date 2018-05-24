package com.intrasoft.handson.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableAutoConfiguration()

@EnableScheduling
@EnableAsync

@ComponentScan(basePackages = {"com.intrasoft"})

@Import({
	/* Database Configuration*/
	DatabaseConfiguration.class
})
public class ExampleApp {

	/**
	 * Initializes this application when running as a standalone application.
	 */
	public static void main(final String[] args) {

		SpringApplication.run(ExampleApp.class, args);
		System.out.println("Spring Boot example Up and Running !!!");
	}
}
