package com.paytonkawa.com.authentication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.paytonkawa.com.authentication.config.Constants;
import com.paytonkawa.com.authentication.service.JwtServices;

@SpringBootApplication
@EnableFeignClients
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Bean
	CommandLineRunner start(JwtServices service) {
		return args->{
			System.out.println("authentication service is starting ... ");
			System.out.println(service.getSecret());
			
		};
	}
}
