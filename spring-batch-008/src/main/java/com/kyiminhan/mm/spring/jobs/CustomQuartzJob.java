package com.kyiminhan.mm.spring.jobs;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.log4j.Log4j2;

/**
 * The Class CustomQuartzJob.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/24 <BR>
 *        spring-batch-008 system <BR>
 *        com.kyiminhan.mm.spring.jobs <BR>
 *        CustomQuartzJob.java <BR>
 */

/** The Constant log. */
@Log4j2
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class CustomQuartzJob extends QuartzJobBean {

	/** The job name. */
	private String jobName;
	
	/** The job launcher. */
	private JobLauncher jobLauncher;
	
	/** The job locator. */
	private JobLocator jobLocator;

	public String getJobName() {
		return this.jobName;
	}

	public void setJobName(final String jobName) {
		this.jobName = jobName;
	}

	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {
		try {
			CustomQuartzJob.log.info("executing in the executeInternal START *****  " + this.getClass());

			final ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext()
					.get("applicationContext");
			this.jobLocator = applicationContext.getBean(JobLocator.class);
			this.jobLauncher = applicationContext.getBean(JobLauncher.class);
			final Job job = this.jobLocator.getJob(this.jobName);
			final JobParameters params = new JobParametersBuilder()
					.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();

			this.jobLauncher.run(job, params);

			CustomQuartzJob.log.info("executing in the executeInternal END *****  " + this.getClass());
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}