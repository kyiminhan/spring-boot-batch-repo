package com.kyiminhan.mm.spring.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * The Class CustomQuartzJob.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/21 <BR>
 *        spring-batch-007 system <BR>
 *        com.kyiminhan.mm.spring.jobs <BR>
 *        CustomQuartzJob.java <BR>
 */
@Log4j2
public class CustomQuartzJob extends QuartzJobBean {

	/** The job name. */
	@Getter
	@Setter
	private String jobName;

	/** The job launcher. */
	@Getter
	@Setter
	private JobLauncher jobLauncher;

	/** The job locator. */
	@Getter
	@Setter
	private JobLocator jobLocator;

	/**
	 * Execute internal.
	 *
	 * @param context the context
	 * @throws JobExecutionException the job execution exception
	 */
	@Override
	protected void executeInternal(final JobExecutionContext context) throws JobExecutionException {

		try {

			CustomQuartzJob.log.warn("jobLocator value ->", this.jobLocator);
			CustomQuartzJob.log.warn("jobLauncher value ->", this.jobLauncher);

			final Job job = this.jobLocator.getJob(this.jobName);
			final JobParameters params = new JobParametersBuilder()
					.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();

			this.jobLauncher.run(job, params);

		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

}