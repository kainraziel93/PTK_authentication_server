package com.paytonkawa.com.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paytonkawa.com.authentication.dto.CustomerResponse;
import com.paytonkawa.com.authentication.dto.LoginRequest;
import com.paytonkawa.com.authentication.rest_client.CustomerFeignClient;


@Service
public class AuthenticationServices {
	
	@Autowired
	private JwtServices jwtService;
	@Autowired
	private CustomerFeignClient customerFeignClient;

	public ResponseEntity<String> authenticate(LoginRequest loginRequest) {
		ResponseEntity<CustomerResponse> customerResponse = customerFeignClient.getCustomerWithCredentials(loginRequest);
		if(customerResponse.getStatusCode().is2xxSuccessful()) {
			String token = jwtService.createToken(customerResponse.getBody());
			return ResponseEntity.ok(token);
		}
		return ResponseEntity.badRequest().build();
	}

	public ResponseEntity<CustomerResponse> verifyAndDecryptToken(String token) {
		
		CustomerResponse customerResponse=  this.jwtService.decryptToken(token);
		if(customerResponse!=null) {
			return ResponseEntity.ok(customerResponse);
		}
		return ResponseEntity.badRequest().build();
	}

}
