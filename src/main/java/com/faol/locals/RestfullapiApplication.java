package com.faol.locals;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfullapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RestfullapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Successfull start!");
	}
}
