package com.kyiminhan.mm.spring.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.spring.entity.Employee;

import lombok.extern.log4j.Log4j2;

/**
 * The Class ConsoleWriter.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-004 system <BR>
 *        com.kyiminhan.mm.spring.writer <BR>
 *        ConsoleWriter.java <BR>
 */
@Log4j2
@Component
public class ConsoleWriter implements ItemWriter<Employee> {

	/**
	 * Write.
	 *
	 * @param employees the employees
	 * @throws Exception the exception
	 */
	@Override
	public void write(final List<? extends Employee> employees) throws Exception {
		ConsoleWriter.log.info(employees);
	}
}