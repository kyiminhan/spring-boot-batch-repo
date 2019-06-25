package com.kyiminhan.mm.spring.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.kyiminhan.mm.spring.entity.Employee;

/**
 * The Interface EmployeeService.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/25 <BR>
 *        spring-batch-014 system <BR>
 *        com.kyiminhan.mm.spring.service <BR>
 *        EmployeeService.java <BR>
 */
public interface EmployeeService {

	/**
	 * Save.
	 *
	 * @param employee the employee
	 */
	@Transactional(rollbackFor = Exception.class)
	void save(Employee employee);

	/**
	 * Save.
	 *
	 * @param employees the employees
	 * @throws Exception the exception
	 */
	@Transactional(rollbackFor = Exception.class)
	void save(List<Employee> employees) throws Exception;
}