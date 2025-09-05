package com.codeoncewidakash.service;

import com.codeoncewidakash.dto.EmployeeReqDto;
import com.codeoncewidakash.dto.EmployeeResDto;

public interface IEmployeeService {
	public EmployeeResDto registerEmployee(EmployeeReqDto employeeReqDto, Long deptId);
	public EmployeeResDto getEmployeeById(Long empId);
}
