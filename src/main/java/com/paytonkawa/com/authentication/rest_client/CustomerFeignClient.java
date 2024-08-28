package com.paytonkawa.com.authentication.rest_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.paytonkawa.com.authentication.dto.CustomerResponse;
import com.paytonkawa.com.authentication.dto.LoginRequest;

@FeignClient(name="customer-service",url = "http://localhost:8080/customer")
public interface CustomerFeignClient {

	@PostMapping("customer_by_credentials")
	public ResponseEntity<CustomerResponse> getCustomerWithCredentials(LoginRequest loginRequest);
}
