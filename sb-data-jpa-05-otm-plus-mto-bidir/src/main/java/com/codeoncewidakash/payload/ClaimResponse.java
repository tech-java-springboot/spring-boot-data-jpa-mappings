package com.codeoncewidakash.payload;

import java.time.LocalDate;

public record ClaimResponse(Long claimId, String claimNumber, String claimType, Double claimAmount, String status, com.codeoncewidakash.entity.Policy policy, LocalDate createdOn, LocalDate updatedOn) {

}
