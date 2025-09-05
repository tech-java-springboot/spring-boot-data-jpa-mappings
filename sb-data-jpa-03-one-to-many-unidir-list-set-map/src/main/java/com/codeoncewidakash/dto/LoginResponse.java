package com.codeoncewidakash.dto;

import java.io.Serializable;

public record LoginResponse(String token, String note) implements Serializable {

}
