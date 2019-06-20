package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

/**
 * The listener interface for receiving stepSkip events. The class that is
 * interested in processing a stepSkip event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addStepSkipListener<code> method. When the stepSkip event
 * occurs, that object's appropriate method is invoked.
 *
 * @see StepSkipEvent
 */
@Component
public class StepSkipListener implements SkipListener<String, Number> {

	/**
	 * On skip in read.
	 *
	 * @param t the t
	 */
	@Override
	public void onSkipInRead(final Throwable t) {
		System.out.println("**************************************************");
		System.out.println("StepSkipListener - onSkipInRead" + this.getClass());
		System.out.println("**************************************************");
	}

	/**
	 * On skip in write.
	 *
	 * @param item the item
	 * @param t    the t
	 */
	@Override
	public void onSkipInWrite(final Number item, final Throwable t) {
		System.out.println("**************************************************");
		System.out.println("StepSkipListener - onSkipInWrite" + this.getClass());
		System.out.println("**************************************************");
	}

	/**
	 * On skip in process.
	 *
	 * @param item the item
	 * @param t    the t
	 */
	@Override
	public void onSkipInProcess(final String item, final Throwable t) {
		System.out.println("**************************************************");
		System.out.println("StepSkipListener - onSkipInProcess" + this.getClass());
		System.out.println("**************************************************");
	}
}