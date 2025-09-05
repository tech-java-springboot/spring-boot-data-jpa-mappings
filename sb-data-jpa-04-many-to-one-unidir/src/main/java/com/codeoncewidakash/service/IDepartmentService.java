package com.codeoncewidakash.service;

import com.codeoncewidakash.dto.DepartmentReqDto;
import com.codeoncewidakash.dto.DepartmentResDto;

public interface IDepartmentService {
	public DepartmentResDto createDepartment(DepartmentReqDto departmentReqDto);
}
