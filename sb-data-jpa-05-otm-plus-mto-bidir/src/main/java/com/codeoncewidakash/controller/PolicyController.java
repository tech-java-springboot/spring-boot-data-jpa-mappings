package com.codeoncewidakash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewidakash.payload.PolicyRequest;
import com.codeoncewidakash.payload.PolicyResponse;
import com.codeoncewidakash.service.IPolicyService;

@RestController
@RequestMapping("/api/v1/policy")
public class PolicyController {
	
	private final IPolicyService policyService;
	
	public PolicyController(IPolicyService policyService) {
		this.policyService = policyService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<String> createPolicy(@RequestBody PolicyRequest policyRequest) {
		String response = policyService.createPolicy(policyRequest);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetch/{policyId}")
	public ResponseEntity<PolicyResponse> getPolicyById(@PathVariable(name = "policyId") Long id) {
		PolicyResponse response = policyService.getPolicyById(id);
		return ResponseEntity.ok(response);
	}
	
}

