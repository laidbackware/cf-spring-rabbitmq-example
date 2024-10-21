package com.laidbackware.rabbitmq_spring_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.laidbackware.rabbitmq_spring_example")
public class RabbitmqSpringApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RabbitmqSpringApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitmqSpringApplication.class, args);
	}
}