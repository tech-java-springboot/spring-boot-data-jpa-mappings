package com.codeoncewidakash.service;

import com.codeoncewidakash.entity.Policy;
import com.codeoncewidakash.payload.PolicyRequest;
import com.codeoncewidakash.payload.PolicyResponse;

public interface IPolicyService {
	public String createPolicy(PolicyRequest policyRequest);
	public Policy createPolicyEntity(Policy policy);
	public PolicyResponse getPolicyById(Long policyId);
	public Policy getPolicyEntityById(Long policyId);
}
