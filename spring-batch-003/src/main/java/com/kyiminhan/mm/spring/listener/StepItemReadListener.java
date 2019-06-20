package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepItemRead events. The class that is
 * interested in processing a stepItemRead event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addStepItemReadListener<code> method. When the stepItemRead
 * event occurs, that object's appropriate method is invoked.
 *
 * @see StepItemReadEvent
 */
@Log4j2
@Component
public class StepItemReadListener implements ItemReadListener<String> {

	/**
	 * Before read.
	 */
	@Override
	public void beforeRead() {
		StepItemReadListener.log.info("**************************************************");
		StepItemReadListener.log.info("ItemReadListener - beforeRead" + this.getClass());
		StepItemReadListener.log.info("**************************************************");
	}

	/**
	 * After read.
	 *
	 * @param item the item
	 */
	@Override
	public void afterRead(final String item) {
		StepItemReadListener.log.info("**************************************************");
		StepItemReadListener.log.info("ItemReadListener - afterRead" + this.getClass());
		StepItemReadListener.log.info("**************************************************");
	}

	/**
	 * On read error.
	 *
	 * @param ex the ex
	 */
	@Override
	public void onReadError(final Exception ex) {
		StepItemReadListener.log.info("**************************************************");
		StepItemReadListener.log.info("ItemReadListener - onReadError" + this.getClass());
		StepItemReadListener.log.info("**************************************************");
	}
}