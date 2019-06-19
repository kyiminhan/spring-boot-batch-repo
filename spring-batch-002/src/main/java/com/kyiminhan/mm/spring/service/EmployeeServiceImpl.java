package com.kyiminhan.mm.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.repo.EmployeeRepo;

import lombok.Setter;

@Service
@Qualifier(value = "employeeService")
@Setter(onMethod = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepo repo;

	@Override
	public void save(final Employee employee) {
		this.repo.save(employee);
	}
}