package com.codeoncewidakash.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeoncewidakash.entity.Employee;
import com.codeoncewidakash.repository.EmployeeRepository;
import com.codeoncewidakash.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public String createEmployee(Employee emp) {
		String response = null;
		if(emp!=null) {
			Employee employee = repository.save(emp);
			response = new StringBuilder().append("EMPLOYEE CREATED SUCCESSFULLY WITH ID ").append(employee.getEmpId()).toString();
		} else {
			response = new StringBuilder().append("EMPLOYEE CREATION FAILED, DUE TO INCOMPLETE EMPLOYEE DETAILS!").toString();
		}
		return response;
	}

}
