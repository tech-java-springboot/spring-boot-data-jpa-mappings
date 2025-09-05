package com.codeoncewidakash.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codeoncewidakash.dto.DepartmentReqDto;
import com.codeoncewidakash.dto.DepartmentResDto;
import com.codeoncewidakash.entity.Department;
import com.codeoncewidakash.repo.DepartmentRepo;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	private final DepartmentRepo departmentRepo;
	
	public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
		this.departmentRepo = departmentRepo;
	}
	
	@Transactional
	public DepartmentResDto createDepartment(DepartmentReqDto departmentReqDto) {
		Department department = Department.builder()
		.deptName(departmentReqDto.deptName())
		.isActive(Boolean.TRUE)
		.build();
		
		Department savedDepartment = departmentRepo.save(department);
		
		return new DepartmentResDto(savedDepartment.getDeptId(), savedDepartment.getDeptName(), savedDepartment.getIsActive());
	}

}
