package com.paytonkawa.com.authentication.config;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.algorithms.Algorithm;

public class Constants {
	
	
	public static Algorithm algorithm(String secret) {
		return Algorithm.HMAC256(secret);
	}
	

}
