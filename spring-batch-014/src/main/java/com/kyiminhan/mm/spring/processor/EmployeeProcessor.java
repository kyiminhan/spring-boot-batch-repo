package com.kyiminhan.mm.spring.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.spring.entity.Employee;

import lombok.extern.log4j.Log4j2;

/**
 * The Class EmployeeProcessor.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/25 <BR>
 *        spring-batch-014 system <BR>
 *        com.kyiminhan.mm.spring.processor <BR>
 *        EmployeeProcessor.java <BR>
 */
@Component

/** The Constant log. */
@Log4j2
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

	@Override
	public Employee process(final Employee employee) throws Exception {
		EmployeeProcessor.log.info("ItemProcessor<Employee, Employee>  " + this.getClass());
		return employee;
	}
}