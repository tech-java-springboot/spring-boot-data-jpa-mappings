package com.codeoncewidakash.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.codeoncewidakash.entity.Department;

public record EmployeeResDto(Long id, String name, String desg, String email, Long mobile, Boolean isActive, LocalDate createdOn, LocalDate updatedOn, Department department) implements Serializable {

}
