package com.codeoncewidakash.dto;

import java.io.Serializable;

public record DepartmentResDto(Long id, String deptName, Boolean isActive) implements Serializable {

}
