package com.codeoncewidakash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewidakash.entity.Employee;
import com.codeoncewidakash.exception.EmployeeNotFoundException;
import com.codeoncewidakash.service.IEmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
	@Autowired
	private IEmployeeService service;

	@PostMapping("/create")
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		String response = service.createEmployee(employee);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/get/{empID}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("empID") Integer empId) {
		Employee employee = null;
		try {
			employee = service.getEmployeeById(empId);
		} catch (EmployeeNotFoundException enfe) {
			throw enfe;
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
	}
}
