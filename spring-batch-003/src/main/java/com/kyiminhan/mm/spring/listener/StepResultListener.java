package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

/**
 * The listener interface for receiving stepResult events. The class that is
 * interested in processing a stepResult event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addStepResultListener<code> method. When the stepResult
 * event occurs, that object's appropriate method is invoked.
 *
 * @see StepResultEvent
 */
@Component
public class StepResultListener implements StepExecutionListener {

	/**
	 * Before step.
	 *
	 * @param stepExecution the step execution
	 */
	@Override
	public void beforeStep(final StepExecution stepExecution) {
		System.out.println("**************************************************");
		System.out.println("Called beforeStep()." + this.getClass());
		System.out.println("**************************************************");
	}

	/**
	 * After step.
	 *
	 * @param stepExecution the step execution
	 * @return ExitStatus
	 */
	@Override
	public ExitStatus afterStep(final StepExecution stepExecution) {
		System.out.println("**************************************************");
		System.out.println("Called afterStep()." + this.getClass());
		System.out.println("**************************************************");
		return ExitStatus.COMPLETED;
	}
}