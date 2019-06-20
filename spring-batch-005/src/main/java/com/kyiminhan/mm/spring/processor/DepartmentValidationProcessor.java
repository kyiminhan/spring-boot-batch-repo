package com.kyiminhan.mm.spring.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.spring.entity.Department;

import lombok.extern.log4j.Log4j2;

/**
 * The Class DepartmentValidationProcessor.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-005 system <BR>
 *        com.kyiminhan.mm.spring.processor <BR>
 *        DepartmentValidationProcessor.java <BR>
 */
@Log4j2
@Component
public class DepartmentValidationProcessor implements ItemProcessor<Department, Department> {

	/**
	 * Process.
	 *
	 * @param department the department
	 * @return Department
	 * @throws Exception the exception
	 */
	@Override
	public Department process(final Department department) throws Exception {

		if (department.getId() == null) {
			DepartmentValidationProcessor.log.info("Missing department id : " + department.getId());
			return null;
		}

		try {
			if (Integer.valueOf(department.getId()) <= 0) {
				DepartmentValidationProcessor.log.info("Invalid department id : " + department.getId());
				return null;
			}
		} catch (final NumberFormatException e) {
			DepartmentValidationProcessor.log.info("Invalid department id : " + department.getId());
			return null;
		}

		return department;
	}
}