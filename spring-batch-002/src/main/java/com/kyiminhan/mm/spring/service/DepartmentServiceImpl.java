package com.kyiminhan.mm.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kyiminhan.mm.spring.entity.Department;
import com.kyiminhan.mm.spring.repo.DepartmentRepo;

import lombok.Setter;

/**
 * The Class DepartmentServiceImpl.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-002 system <BR>
 *        com.kyiminhan.mm.spring.service <BR>
 *        DepartmentServiceImpl.java <BR>
 */
@Service
@Qualifier(value = "departmentService")
@Setter(onMethod = @__(@Autowired))
public class DepartmentServiceImpl implements DepartmentService {

	/** The repo. */
	private DepartmentRepo repo;

	/**
	 * Save.
	 *
	 * @param department the department
	 */
	@Override
	public void save(final Department department) {
		this.repo.save(department);
	}
}