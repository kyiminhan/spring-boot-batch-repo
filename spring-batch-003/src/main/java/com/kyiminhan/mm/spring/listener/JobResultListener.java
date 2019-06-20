package com.kyiminhan.mm.spring.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * The listener interface for receiving jobResult events. The class that is
 * interested in processing a jobResult event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addJobResultListener<code> method. When the jobResult event
 * occurs, that object's appropriate method is invoked.
 *
 * @see JobResultEvent
 */
@Log4j2
@Component
public class JobResultListener implements JobExecutionListener {

	/**
	 * Before job.
	 *
	 * @param jobExecution the job execution
	 */
	@Override
	public void beforeJob(final JobExecution jobExecution) {
		JobResultListener.log.info("**************************************************");
		JobResultListener.log.info("Called beforeJob()." + this.getClass());
		JobResultListener.log.info("**************************************************");
	}

	/**
	 * After job.
	 *
	 * @param jobExecution the job execution
	 */
	@Override
	public void afterJob(final JobExecution jobExecution) {
		JobResultListener.log.info("**************************************************");
		JobResultListener.log.info("Called afterJob()." + this.getClass());
		JobResultListener.log.info("**************************************************");
	}
}