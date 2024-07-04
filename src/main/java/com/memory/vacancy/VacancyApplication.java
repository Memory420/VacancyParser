package com.memory.vacancy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.memory")
public class VacancyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VacancyApplication.class, args);
	}

}
