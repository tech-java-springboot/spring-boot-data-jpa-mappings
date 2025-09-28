package com.codeoncewidakash.service.impl;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.entity.Claim;
import com.codeoncewidakash.entity.Policy;
import com.codeoncewidakash.payload.ClaimRequest;
import com.codeoncewidakash.payload.PolicyResponse;
import com.codeoncewidakash.repo.ClaimRepo;
import com.codeoncewidakash.service.IClaimService;
import com.codeoncewidakash.service.IPolicyService;

@Service("claimService")
public class ClaimServiceImpl implements IClaimService {
	
	private ClaimRepo claimRepo;
	private IPolicyService policyService;
	
	public ClaimServiceImpl(ClaimRepo claimRepo, IPolicyService policyService) {
		this.claimRepo = claimRepo;
		this.policyService = policyService;
	}

	@Override
	public String createClaim(ClaimRequest claimRequest) {
		//1. fetch policy
		Policy policy = policyService.getPolicyEntityById(claimRequest.policyId());
		
		//2. convert request to entity
		Claim claim = Claim.builder()
				.claimNumber(claimRequest.claimNumber())
				.claimAmount(claimRequest.claimAmount())
				.claimType(claimRequest.claimType())
				.status(claimRequest.status())
				.policy(policy)
				.build();
		
		Claim submittedClaim = claimRepo.save(claim);
		
		return "CLAIM SUBMITTED SUCCESSFULLY WITH ID '"+submittedClaim.getId()+"'";
	}

}
