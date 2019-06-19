package com.kyiminhan.mm.spring.service;

import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.spring.entity.Department;

/**
 * The Interface DepartmentService.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/19 <BR>
 * spring-batch-002 system <BR>
 * com.kyiminhan.mm.spring.service <BR>
 * DepartmentService.java <BR>
 */
public interface DepartmentService {

	/**
	 * Save.
	 *
	 * @param department the department
	 */
	@Transactional(rollbackFor = Exception.class)
	void save(Department department);
}