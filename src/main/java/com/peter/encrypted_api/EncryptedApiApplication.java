package com.peter.encrypted_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EncryptedApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptedApiApplication.class, args);

		System.out.println("Connected to database");
	}

}
