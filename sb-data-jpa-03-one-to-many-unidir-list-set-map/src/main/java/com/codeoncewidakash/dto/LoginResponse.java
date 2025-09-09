package com.codeoncewidakash.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoginResponse(String accessToken, String refreshToken, String note) implements Serializable {

}
