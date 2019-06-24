package com.kyiminhan.mm.spring.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The Class ConsoleWriter.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @param <T> the generic type
 * @since 2019/06/24 <BR>
 *        spring-batch-013 system <BR>
 *        com.kyiminhan.mm.spring.writer <BR>
 *        ConsoleWriter.java <BR>
 */
@Component

/** The Constant log. */

/** The Constant log. */
@Log4j2
public class ConsoleWriter<T> implements ItemWriter<T> {

	/**
	 * Write.
	 *
	 * @param items the items
	 * @throws Exception the exception
	 */
	@Override
	public void write(final List<? extends T> items) throws Exception {
		items.forEach(t -> ConsoleWriter.log.info(t));
	}
}