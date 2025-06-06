package com.example;

import jakarta.persistence.Entity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableJpaRepositories(basePackages = "com.example.repository")

public class StudentDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentDataApplication.class, args);

		System.out.println("Hello, World!");
	}
}
