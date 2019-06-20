package com.kyiminhan.mm.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.repo.EmployeeRepo;

import lombok.Setter;

/**
 * The Class EmployeeServiceImpl.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-002 system <BR>
 *        com.kyiminhan.mm.spring.service <BR>
 *        EmployeeServiceImpl.java <BR>
 */
@Service
@Qualifier(value = "employeeService")
@Setter(onMethod = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

	/** The repo. */
	private EmployeeRepo repo;

	/**
	 * Save.
	 *
	 * @param employee the employee
	 */
	@Override
	public void save(final Employee employee) {
		this.repo.save(employee);
	}
}