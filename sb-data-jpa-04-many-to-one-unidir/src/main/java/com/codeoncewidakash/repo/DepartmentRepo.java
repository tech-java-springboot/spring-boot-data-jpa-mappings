package com.codeoncewidakash.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeoncewidakash.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
