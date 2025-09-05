package com.codeoncewidakash.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codeoncewidakash.dto.EmployeeReqDto;
import com.codeoncewidakash.dto.EmployeeResDto;
import com.codeoncewidakash.entity.Department;
import com.codeoncewidakash.entity.Employee;
import com.codeoncewidakash.exception.DepartmentNotFoundException;
import com.codeoncewidakash.exception.EmployeeNotFoundException;
import com.codeoncewidakash.repo.DepartmentRepo;
import com.codeoncewidakash.repo.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private EmployeeRepo employeeRepo;
	private DepartmentRepo departmentRepo;
	
	public EmployeeServiceImpl(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo) {
		this.employeeRepo = employeeRepo;
		this.departmentRepo = departmentRepo;
	}
	
	private Department getDepartmentById(Long deptId) {
		return departmentRepo.findById(deptId).orElseThrow(() -> new DepartmentNotFoundException("DEPARTMENT WITH ID '"+deptId+"' IS NOT FOUND!"));
	}
	
	@Override
	public EmployeeResDto registerEmployee(EmployeeReqDto employeeReqDto, Long deptId) {
		Employee employee = Employee.builder()
		.empName(employeeReqDto.name())
		.empDesg(employeeReqDto.desg())
		.empEmail(employeeReqDto.email())
		.empMob(employeeReqDto.mobile())
		.isActive(true)
		.department(getDepartmentById(deptId))
		.build();
		
		Employee registeredEmp = employeeRepo.save(employee);
		
		return new EmployeeResDto(registeredEmp.getEmpId(), registeredEmp.getEmpName(), registeredEmp.getEmpDesg(), registeredEmp.getEmpEmail(), registeredEmp.getEmpMob(), registeredEmp.getIsActive(), registeredEmp.getCreatedOn(), registeredEmp.getUpdatedOn(), registeredEmp.getDepartment());
	}
	
	@Override
	public EmployeeResDto getEmployeeById(Long empId) {
		Employee employee = employeeRepo.findById(empId).orElseThrow(() -> new EmployeeNotFoundException("EMPLOYEE WITH ID '"+empId+"' NOT FOUND!"));
		return new EmployeeResDto(employee.getEmpId(), employee.getEmpName(), employee.getEmpDesg(), employee.getEmpEmail(), employee.getEmpMob(), employee.getIsActive(), employee.getCreatedOn(), employee.getUpdatedOn(), employee.getDepartment());
	}

}
