package com.paytonkawa.com.authentication.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.paytonkawa.com.authentication.config.Constants;
import com.paytonkawa.com.authentication.dto.CustomerResponse;

@Service
public class JwtServices {

	@Value("${ptk.secret}")
	private String secret;
	
	public String getSecret() {
		return secret;
	}
	
	public JwtServices() {
		
	}

	public void checkToken(String token) {
		JWT.require(Constants.algorithm(secret)).build().verify(token);
	}
	

	
	public String createToken(CustomerResponse customerResponse) {
		return  JWT.create()
				.withSubject(customerResponse.getEmail())
				.withClaim("id", customerResponse.getId())
				.withClaim("role", customerResponse.getRole())
				.withExpiresAt(new Date(System.currentTimeMillis()+60*60*60*1000))
				.sign(Constants.algorithm(secret));
	}
	
	  public CustomerResponse decryptToken(String token){
		  try {
			  DecodedJWT decodedJwt= JWT.require(Constants.algorithm(secret)).build().verify(token);
				CustomerResponse customerResponse = new CustomerResponse();
				customerResponse.setEmail((String)decodedJwt.getSubject());
				customerResponse.setId(decodedJwt.getClaim("id").asInt());
				customerResponse.setRole(decodedJwt.getClaim("role").asString());
				return customerResponse;
		} catch (Exception e) {
			System.out.println("error while verifying the token =>"+e.getMessage());
			return null;
		}
		
	}
	/*
	public JWTVerifier buildToken(String username) {
		return JWT.require(Constants.algorithm()).build();
	}*/
	
}
