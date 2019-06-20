package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepItemProcess events. The class that
 * is interested in processing a stepItemProcess event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's <code>addStepItemProcessListener<code>
 * method. When the stepItemProcess event occurs, that object's appropriate
 * method is invoked.
 *
 * @see StepItemProcessEvent
 */
@Log4j2
@Component
public class StepItemProcessListener implements ItemProcessListener<String, Number> {

	/**
	 * Before process.
	 *
	 * @param item the item
	 */
	@Override
	public void beforeProcess(final String item) {
		StepItemProcessListener.log.info("**************************************************");
		StepItemProcessListener.log.info("ItemProcessListener - beforeProcess" + this.getClass());
		StepItemProcessListener.log.info("**************************************************");
	}

	@Override
	public void afterProcess(final String item, final Number result) {
		StepItemProcessListener.log.info("**************************************************");
		StepItemProcessListener.log.info("ItemProcessListener - afterProcess" + this.getClass());
		StepItemProcessListener.log.info("**************************************************");
	}

	@Override
	public void onProcessError(final String item, final Exception e) {
		StepItemProcessListener.log.info("**************************************************");
		StepItemProcessListener.log.info("ItemProcessListener - onProcessError" + this.getClass());
		StepItemProcessListener.log.info("**************************************************");
	}
}