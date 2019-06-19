package com.kyiminhan.mm.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kyiminhan.mm.spring.entity.Department;
import com.kyiminhan.mm.spring.repo.DepartmentRepo;

import lombok.Setter;

@Service
@Qualifier(value = "departmentService")
@Setter(onMethod = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepo repo;

	@Override
	public void save(final Department department) {
		this.repo.save(department);
	}
}