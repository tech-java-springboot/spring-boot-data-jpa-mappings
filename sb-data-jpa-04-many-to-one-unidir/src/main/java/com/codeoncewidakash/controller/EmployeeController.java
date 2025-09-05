package com.codeoncewidakash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.codeoncewidakash.dto.EmployeeReqDto;
import com.codeoncewidakash.dto.EmployeeResDto;
import com.codeoncewidakash.service.IEmployeeService;

@RestController
@RequestMapping("/api/v1/emp")
public class EmployeeController {
	
	private IEmployeeService employeeService;
	
	public EmployeeController(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping("/register/{deptId}")
	public ResponseEntity<EmployeeResDto> registerEmployee(@RequestBody EmployeeReqDto employeeReqDto, @PathVariable(name = "deptId") Long deptId){
		EmployeeResDto employeeResponse = employeeService.registerEmployee(employeeReqDto, deptId);
		return new ResponseEntity<>(employeeResponse, HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{empId}")
	public ResponseEntity<EmployeeResDto> getEmployeeById(@PathVariable(name = "empId") Long employeeId){
		EmployeeResDto employeeResponse = employeeService.getEmployeeById(employeeId);
		return new ResponseEntity<>(employeeResponse, HttpStatus.FOUND);
	}
}
