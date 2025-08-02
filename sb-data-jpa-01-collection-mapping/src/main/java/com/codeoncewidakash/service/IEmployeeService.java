package com.codeoncewidakash.service;

import com.codeoncewidakash.entity.Employee;

public interface IEmployeeService {
	public String createEmployee(Employee emp);
	public Employee getEmployeeById(Integer empId);
}
