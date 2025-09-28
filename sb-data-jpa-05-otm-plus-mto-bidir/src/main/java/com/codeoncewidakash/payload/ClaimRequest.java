package com.codeoncewidakash.payload;

public record ClaimRequest(String claimNumber, String claimType, Double claimAmount, String status, Long policyId) {
	
}
