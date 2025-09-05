package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
