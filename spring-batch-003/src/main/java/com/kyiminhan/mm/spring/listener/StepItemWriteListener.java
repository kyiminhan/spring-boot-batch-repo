package com.kyiminhan.mm.spring.listener;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepItemWrite events. The class that is
 * interested in processing a stepItemWrite event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addStepItemWriteListener<code> method. When the
 * stepItemWrite event occurs, that object's appropriate method is invoked.
 *
 * @see StepItemWriteEvent
 */
@Log4j2
@Component
public class StepItemWriteListener implements ItemWriteListener<Number> {

	/**
	 * Before write.
	 *
	 * @param items the items
	 */
	@Override
	public void beforeWrite(final List<? extends Number> items) {
		StepItemWriteListener.log.info("**************************************************");
		StepItemWriteListener.log.info("ItemWriteListener - beforeWrite" + this.getClass());
		StepItemWriteListener.log.info("**************************************************");
	}

	/**
	 * After write.
	 *
	 * @param items the items
	 */
	@Override
	public void afterWrite(final List<? extends Number> items) {
		StepItemWriteListener.log.info("**************************************************");
		StepItemWriteListener.log.info("ItemWriteListener - afterWrite" + this.getClass());
		StepItemWriteListener.log.info("**************************************************");
	}

	/**
	 * On write error.
	 *
	 * @param exception the exception
	 * @param items     the items
	 */
	@Override
	public void onWriteError(final Exception exception, final List<? extends Number> items) {
		StepItemWriteListener.log.info("**************************************************");
		StepItemWriteListener.log.info("ItemWriteListener - onWriteError" + this.getClass());
		StepItemWriteListener.log.info("**************************************************");
	}
}