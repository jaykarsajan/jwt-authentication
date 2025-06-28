package com.example.spring_security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthentication {

	public static void main(String[] args) {
		SpringApplication.run(JwtAuthentication.class, args);
		System.out.println("JWT authentication project started");
	}

}
