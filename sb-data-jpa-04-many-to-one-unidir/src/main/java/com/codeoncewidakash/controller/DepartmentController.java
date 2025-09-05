package com.codeoncewidakash.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeoncewidakash.dto.DepartmentReqDto;
import com.codeoncewidakash.dto.DepartmentResDto;
import com.codeoncewidakash.service.IDepartmentService;

@RestController
@RequestMapping("/api/v1/dept")
public class DepartmentController {

	private IDepartmentService departmentService;
	
	public DepartmentController(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@PostMapping("/create")
	public ResponseEntity<DepartmentResDto> createDepartment(@RequestBody DepartmentReqDto departmentReqDto){
		DepartmentResDto departmentResponse = departmentService.createDepartment(departmentReqDto);
		return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
	}
}
