package com.codeoncewidakash.dto;

import java.io.Serializable;

public record EmployeeReqDto(String name, String desg, String email, Long mobile) implements Serializable {

}
