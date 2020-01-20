package com.xcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class XcarApplication {

	public static void main(String[] args) {
		SpringApplication.run(XcarApplication.class, args);
	}

}
