package com.codeoncewidakash.service.impl;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.entity.Policy;
import com.codeoncewidakash.exception.PolicyNotFoundException;
import com.codeoncewidakash.payload.PolicyRequest;
import com.codeoncewidakash.payload.PolicyResponse;
import com.codeoncewidakash.repo.PolicyRepo;
import com.codeoncewidakash.service.IPolicyService;

@Service("policyService")
public class PolicyServiceImpl implements IPolicyService {
	
	private PolicyRepo policyRepo;
	
	public PolicyServiceImpl(PolicyRepo policyRepo) {
		this.policyRepo = policyRepo;
	}
	
	@Override
	public String createPolicy(PolicyRequest policyRequest) {
		//1. convert request to entity
		Policy policyEntity = Policy.builder()
		.policyNumber(policyRequest.policyNumber())
		.policyType(policyRequest.policyType())
		.policyHolderName(policyRequest.policyHolderName())
		.build();
		Policy createdPolicy = policyRepo.save(policyEntity);
		return "POLICY CREATED WITH ID '"+createdPolicy.getId()+"'";
	}
	
	@Override
	public Policy createPolicyEntity(Policy policy) {
		return policyRepo.save(policy);
	}
	
	@Override
	public PolicyResponse getPolicyById(Long policyId) {
		Policy policy = policyRepo.findById(policyId).orElseThrow(() -> new PolicyNotFoundException("POLICY NOT FOUND WITH ID '"+policyId+"'"));
		return new PolicyResponse(policy.getId(), policy.getPolicyNumber(), policy.getPolicyType(), policy.getPolicyHolderName(), policy.getClaims());
	}
	
	@Override
	public Policy getPolicyEntityById(Long policyId) {
		return policyRepo.findById(policyId).orElseThrow(() -> new PolicyNotFoundException("POLICY NOT FOUNDE WITH ID '"+policyId+"'"));
	}
	
}
