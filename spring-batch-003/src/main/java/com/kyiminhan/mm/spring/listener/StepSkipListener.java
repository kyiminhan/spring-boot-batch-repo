package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepSkip events. The class that is
 * interested in processing a stepSkip event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addStepSkipListener<code> method. When the stepSkip event
 * occurs, that object's appropriate method is invoked.
 *
 * @see StepSkipEvent
 */
@Log4j2
@Component
public class StepSkipListener implements SkipListener<String, Number> {

	/**
	 * On skip in read.
	 *
	 * @param t the t
	 */
	@Override
	public void onSkipInRead(final Throwable t) {
		StepSkipListener.log.info("**************************************************");
		StepSkipListener.log.info("StepSkipListener - onSkipInRead" + this.getClass());
		StepSkipListener.log.info("**************************************************");
	}

	/**
	 * On skip in write.
	 *
	 * @param item the item
	 * @param t    the t
	 */
	@Override
	public void onSkipInWrite(final Number item, final Throwable t) {
		StepSkipListener.log.info("**************************************************");
		StepSkipListener.log.info("StepSkipListener - onSkipInWrite" + this.getClass());
		StepSkipListener.log.info("**************************************************");
	}

	/**
	 * On skip in process.
	 *
	 * @param item the item
	 * @param t    the t
	 */
	@Override
	public void onSkipInProcess(final String item, final Throwable t) {
		StepSkipListener.log.info("**************************************************");
		StepSkipListener.log.info("StepSkipListener - onSkipInProcess" + this.getClass());
		StepSkipListener.log.info("**************************************************");
	}
}