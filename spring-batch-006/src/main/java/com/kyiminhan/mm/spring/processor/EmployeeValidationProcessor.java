package com.kyiminhan.mm.spring.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.spring.entity.Employee;

import lombok.extern.log4j.Log4j2;

/**
 * The Class EmployeeValidationProcessor.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-006 system <BR>
 *        com.kyiminhan.mm.spring.processor <BR>
 *        EmployeeValidationProcessor.java <BR>
 */

/** The Constant log. */
@Log4j2
@Component
public class EmployeeValidationProcessor implements ItemProcessor<Employee, Employee> {

	/**
	 * Process.
	 *
	 * @param employee the employee
	 * @return Employee
	 * @throws Exception the exception
	 */
	@Override
	public Employee process(final Employee employee) throws Exception {

		if (employee.getId() == null) {
			EmployeeValidationProcessor.log.info("Missing employee id : " + employee.getId());
			return null;
		}

		try {
			if (Integer.valueOf(employee.getId()) <= 0) {
				EmployeeValidationProcessor.log.info("Invalid employee id : " + employee.getId());
				return null;
			}
		} catch (final NumberFormatException e) {
			EmployeeValidationProcessor.log.info("Invalid employee id : " + employee.getId());
			return null;
		}

		return employee;
	}
}