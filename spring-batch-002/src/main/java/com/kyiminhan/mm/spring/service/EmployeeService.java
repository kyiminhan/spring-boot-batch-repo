package com.kyiminhan.mm.spring.service;

import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.spring.entity.Employee;

public interface EmployeeService {

	@Transactional(rollbackFor = Exception.class)
	void save(Employee employee);
}