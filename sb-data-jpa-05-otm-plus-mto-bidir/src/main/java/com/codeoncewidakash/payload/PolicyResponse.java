package com.codeoncewidakash.payload;

import com.codeoncewidakash.entity.Claim;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

public record PolicyResponse(Long policyId, String policyNumber, String policyType, String policyHolderName, @JsonInclude(JsonInclude.Include.NON_EMPTY) java.util.List<Claim> claims) {

}
