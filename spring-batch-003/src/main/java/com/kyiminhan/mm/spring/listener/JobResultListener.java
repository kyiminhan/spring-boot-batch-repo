package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

/**
 * The listener interface for receiving jobResult events. The class that is
 * interested in processing a jobResult event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addJobResultListener<code> method. When the jobResult event
 * occurs, that object's appropriate method is invoked.
 *
 * @see JobResultEvent
 */
@Component
public class JobResultListener implements JobExecutionListener {

	/**
	 * Before job.
	 *
	 * @param jobExecution the job execution
	 */
	@Override
	public void beforeJob(final JobExecution jobExecution) {
		System.out.println("**************************************************");
		System.out.println("Called beforeJob()." + this.getClass());
		System.out.println("**************************************************");
	}

	/**
	 * After job.
	 *
	 * @param jobExecution the job execution
	 */
	@Override
	public void afterJob(final JobExecution jobExecution) {
		System.out.println("**************************************************");
		System.out.println("Called afterJob()." + this.getClass());
		System.out.println("**************************************************");
	}
}