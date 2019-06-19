package com.kyiminhan.mm.spring.service;

import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.spring.entity.Department;

public interface DepartmentService {

	@Transactional(rollbackFor = Exception.class)
	void save(Department department);
}