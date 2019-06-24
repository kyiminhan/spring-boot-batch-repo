package com.kyiminhan.mm.spring.itemSteam;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The Class ItemCountItemStream.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/24 <BR>
 *        spring-batch-012 system <BR>
 *        com.kyiminhan.mm.spring.itemSteam <BR>
 *        ItemCountItemStream.java <BR>
 */
@Component

/** The Constant log. */
@Log4j2
public class ItemCountItemStream implements ItemStream {

	/**
	 * Open.
	 *
	 * @param executionContext the execution context
	 * @throws ItemStreamException the item stream exception
	 */
	@Override
	public void open(final ExecutionContext executionContext) throws ItemStreamException {

	}

	/**
	 * Update.
	 *
	 * @param executionContext the execution context
	 * @throws ItemStreamException the item stream exception
	 */
	@Override
	public void update(final ExecutionContext executionContext) throws ItemStreamException {
		ItemCountItemStream.log.info("ItemCount: " + executionContext.get("FlatFileItemReader.read.count"));
	}

	/**
	 * Close.
	 *
	 * @throws ItemStreamException the item stream exception
	 */
	@Override
	public void close() throws ItemStreamException {

	}
}