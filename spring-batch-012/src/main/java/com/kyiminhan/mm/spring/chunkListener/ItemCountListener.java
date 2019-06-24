package com.kyiminhan.mm.spring.chunkListener;

import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving itemCount events. The class that is
 * interested in processing a itemCount event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addItemCountListener<code> method. When the itemCount event
 * occurs, that object's appropriate method is invoked.
 *
 * @see ItemCountEvent
 */
@Component

/** The Constant log. */
@Log4j2
public class ItemCountListener implements ChunkListener {

	/**
	 * Before chunk.
	 *
	 * @param context the context
	 */
	@Override
	public void beforeChunk(final ChunkContext context) {
		// ItemCountListener.log.info("beforeChunk ......");
	}

	/**
	 * After chunk.
	 *
	 * @param context the context
	 */
	@Override
	public void afterChunk(final ChunkContext context) {

		// ItemCountListener.log.info("afterChunk ......");
		final int count = context.getStepContext().getStepExecution().getReadCount();
		ItemCountListener.log.info("ItemCount: " + count);
	}

	/**
	 * After chunk error.
	 *
	 * @param context the context
	 */
	@Override
	public void afterChunkError(final ChunkContext context) {
		// ItemCountListener.log.info("afterChunkError ......");
	}
}