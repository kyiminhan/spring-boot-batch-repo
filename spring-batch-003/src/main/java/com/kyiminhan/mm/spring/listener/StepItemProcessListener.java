package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

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
@Component
public class StepItemProcessListener implements ItemProcessListener<String, Number> {

	/**
	 * Before process.
	 *
	 * @param item the item
	 */
	@Override
	public void beforeProcess(final String item) {
		System.out.println("**************************************************");
		System.out.println("ItemProcessListener - beforeProcess" + this.getClass());
		System.out.println("**************************************************");
	}

	@Override
	public void afterProcess(final String item, final Number result) {
		System.out.println("**************************************************");
		System.out.println("ItemProcessListener - afterProcess" + this.getClass());
		System.out.println("**************************************************");
	}

	@Override
	public void onProcessError(final String item, final Exception e) {
		System.out.println("**************************************************");
		System.out.println("ItemProcessListener - onProcessError" + this.getClass());
		System.out.println("**************************************************");
	}
}