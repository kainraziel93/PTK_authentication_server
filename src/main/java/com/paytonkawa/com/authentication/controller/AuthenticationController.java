package com.paytonkawa.com.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paytonkawa.com.authentication.dto.CustomerResponse;
import com.paytonkawa.com.authentication.dto.LoginRequest;
import com.paytonkawa.com.authentication.service.AuthenticationServices;

@RestController
@RequestMapping("authentication")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationServices authenticationServices;

	@PostMapping("authenticate")
	public ResponseEntity<String> authenticate( @RequestBody LoginRequest loginRequest){
		return this.authenticationServices.authenticate(loginRequest);
		
	}
	
	@GetMapping("verify_token")
	public ResponseEntity<CustomerResponse> verifyAndDecryptToken(@RequestParam(name="token") String token){
		return this.authenticationServices.verifyAndDecryptToken(token);
	}
}
