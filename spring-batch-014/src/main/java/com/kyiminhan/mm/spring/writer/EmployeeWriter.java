package com.kyiminhan.mm.spring.writer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.spring.entity.Employee;
import com.kyiminhan.mm.spring.service.EmployeeService;

/**
 * The Class EmployeeWriter.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/25 <BR>
 *        spring-batch-014 system <BR>
 *        com.kyiminhan.mm.spring.writer <BR>
 *        EmployeeWriter.java <BR>
 */
@Component
public class EmployeeWriter extends ConsoleWriter<Employee> {

	/** The employee service. */
	@Autowired
	private EmployeeService employeeService;

	/**
	 * Write.
	 *
	 * @param items the items
	 * @throws Exception the exception
	 */
	@Override
	public void write(final List<? extends Employee> items) throws Exception {
		super.write(items);
		final List<Employee> employees = new ArrayList<>();
		employees.addAll(items);
		this.employeeService.save(employees);
	}
}