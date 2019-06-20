package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving stepResult events. The class that is
 * interested in processing a stepResult event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addStepResultListener<code> method. When the stepResult
 * event occurs, that object's appropriate method is invoked.
 *
 * @see StepResultEvent
 */
@Log4j2
@Component
public class StepResultListener implements StepExecutionListener {

	/**
	 * Before step.
	 *
	 * @param stepExecution the step execution
	 */
	@Override
	public void beforeStep(final StepExecution stepExecution) {
		StepResultListener.log.info("**************************************************");
		StepResultListener.log.info("Called beforeStep()." + this.getClass());
		StepResultListener.log.info("**************************************************");
	}

	/**
	 * After step.
	 *
	 * @param stepExecution the step execution
	 * @return ExitStatus
	 */
	@Override
	public ExitStatus afterStep(final StepExecution stepExecution) {
		StepResultListener.log.info("**************************************************");
		StepResultListener.log.info("Called afterStep()." + this.getClass());
		StepResultListener.log.info("**************************************************");
		return ExitStatus.COMPLETED;
	}
}