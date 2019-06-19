package com.kyiminhan.mm.spring.service;

import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.spring.entity.Employee;

/**
 * The Interface EmployeeService.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/19 <BR>
 * spring-batch-002 system <BR>
 * com.kyiminhan.mm.spring.service <BR>
 * EmployeeService.java <BR>
 */
public interface EmployeeService {

	/**
	 * Save.
	 *
	 * @param employee the employee
	 */
	@Transactional(rollbackFor = Exception.class)
	void save(Employee employee);
}