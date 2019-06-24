package com.kyiminhan.mm.spring.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.kyiminhan.mm.spring.jobs.CustomQuartzJob;

import lombok.Setter;

/**
 * The Class QuartzConfig.<BR>
 *
 * @author KYIMINHAN <BR>
 * @version 1.0 <BR>
 * @since 2019/06/24 <BR>
 *        spring-batch-008 system <BR>
 *        com.kyiminhan.mm.spring.config <BR>
 *        QuartzConfig.java <BR>
 */
@Configuration
@Setter(onMethod = @__(@Autowired))
public class QuartzConfig {

	/** The job launcher. */
	private JobLauncher jobLauncher;

	/** The job locator. */
	private JobLocator jobLocator;

	/**
	 * Job registry bean post processor.
	 *
	 * @param jobRegistry the job registry
	 * @return JobRegistryBeanPostProcessor
	 */
	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(final JobRegistry jobRegistry) {
		final JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}

	/**
	 * Job one detail.
	 *
	 * @return JobDetail
	 */
	@Bean
	public JobDetail jobOneDetail() {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "demoJobOne");
		jobDataMap.put("jobLauncher", this.jobLauncher);
		jobDataMap.put("jobLocator", this.jobLocator);

		return JobBuilder.newJob(CustomQuartzJob.class).withIdentity("demoJobOne", null).setJobData(jobDataMap)
				.storeDurably().build();
	}

	/**
	 * Job two detail.
	 *
	 * @return JobDetail
	 */
	@Bean
	public JobDetail jobTwoDetail() {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "demoJobTwo");
		jobDataMap.put("jobLauncher", this.jobLauncher);
		jobDataMap.put("jobLocator", this.jobLocator);

		return JobBuilder.newJob(CustomQuartzJob.class).withIdentity("demoJobTwo", null).setJobData(jobDataMap)
				.storeDurably().build();
	}

	/**
	 * Job one trigger.
	 *
	 * @return Trigger
	 */
	@Bean
	public Trigger jobOneTrigger() {
		final SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(this.jobOneDetail()).withIdentity("jobOneTrigger", null)
				.withSchedule(scheduleBuilder).build();
	}

	/**
	 * Job two trigger.
	 *
	 * @return Trigger
	 */
	@Bean
	public Trigger jobTwoTrigger() {
		final SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(this.jobTwoDetail()).withIdentity("jobTwoTrigger", null)
				.withSchedule(scheduleBuilder).build();
	}

	/**
	 * Scheduler factory bean.
	 *
	 * @return SchedulerFactoryBean
	 * @throws IOException        Signals that an I/O exception has occurred.
	 * @throws SchedulerException the scheduler exception
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException, SchedulerException {
		final SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(this.jobOneTrigger(), this.jobTwoTrigger());
		scheduler.setQuartzProperties(this.quartzProperties());
		scheduler.setJobDetails(this.jobOneDetail(), this.jobTwoDetail());
		scheduler.setApplicationContextSchedulerContextKey("applicationContext");
		return scheduler;
	}

	/**
	 * Quartz properties.
	 *
	 * @return Properties
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Properties quartzProperties() throws IOException {
		final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
}