package com.springbootstudy.bbs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringbootclassBbs0301Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootclassBbs0301Application.class, args);
	}

}
