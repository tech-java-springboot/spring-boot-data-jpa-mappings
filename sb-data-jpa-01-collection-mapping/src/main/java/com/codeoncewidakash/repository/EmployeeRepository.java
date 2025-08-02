package com.codeoncewidakash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
}
