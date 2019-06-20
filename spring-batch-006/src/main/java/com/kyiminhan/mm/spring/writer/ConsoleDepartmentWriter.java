package com.kyiminhan.mm.spring.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.kyiminhan.mm.spring.entity.Department;

import lombok.extern.log4j.Log4j2;

/**
 * The Class ConsoleDepartmentWriter.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/20 <BR>
 *        spring-batch-006 system <BR>
 *        com.kyiminhan.mm.spring.writer <BR>
 *        ConsoleDepartmentWriter.java <BR>
 */

/** The Constant log. */

/** The Constant log. */
@Log4j2
@Component
public class ConsoleDepartmentWriter implements ItemWriter<Department> {

	/**
	 * Write.
	 *
	 * @param employees the employees
	 * @throws Exception the exception
	 */
	@Override
	public void write(final List<? extends Department> departments) throws Exception {
		ConsoleDepartmentWriter.log.info(departments);
	}
}