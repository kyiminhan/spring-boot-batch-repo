package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

/**
 * The listener interface for receiving stepItemRead events. The class that is
 * interested in processing a stepItemRead event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addStepItemReadListener<code> method. When the stepItemRead
 * event occurs, that object's appropriate method is invoked.
 *
 * @see StepItemReadEvent
 */
@Component
public class StepItemReadListener implements ItemReadListener<String> {

	/**
	 * Before read.
	 */
	@Override
	public void beforeRead() {
		System.out.println("**************************************************");
		System.out.println("ItemReadListener - beforeRead" + this.getClass());
		System.out.println("**************************************************");
	}

	/**
	 * After read.
	 *
	 * @param item the item
	 */
	@Override
	public void afterRead(final String item) {
		System.out.println("**************************************************");
		System.out.println("ItemReadListener - afterRead" + this.getClass());
		System.out.println("**************************************************");
	}

	/**
	 * On read error.
	 *
	 * @param ex the ex
	 */
	@Override
	public void onReadError(final Exception ex) {
		System.out.println("**************************************************");
		System.out.println("ItemReadListener - onReadError" + this.getClass());
		System.out.println("**************************************************");
	}
}