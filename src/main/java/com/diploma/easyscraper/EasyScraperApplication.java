package com.diploma.easyscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class EasyScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyScraperApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder createPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
