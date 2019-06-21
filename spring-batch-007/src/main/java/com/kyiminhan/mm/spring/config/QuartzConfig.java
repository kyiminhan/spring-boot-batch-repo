package com.kyiminhan.mm.spring.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
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

@Configuration
@Setter(onMethod = @__(@Autowired))
public class QuartzConfig {

	private JobLauncher jobLauncher;

	private JobLocator jobLocator;

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(final JobRegistry jobRegistry) {
		final JobRegistryBeanPostProcessor processor = new JobRegistryBeanPostProcessor();
		processor.setJobRegistry(jobRegistry);
		return processor;
	}

	@Bean
	public JobDetail jobOneDetail() {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "demoJobOne");
		jobDataMap.put("jobLuncher", this.jobLauncher);
		jobDataMap.put("jobLocator", this.jobLocator);

		return JobBuilder.newJob(CustomQuartzJob.class).withIdentity("demoJobOne").setJobData(jobDataMap).storeDurably()
				.build();
	}

	@Bean
	public JobDetail jobTwoDetail() {
		final JobDataMap jobDataMap = new JobDataMap();
		jobDataMap.put("jobName", "demoJobTwo");
		jobDataMap.put("jobLauncher", this.jobLauncher);
		jobDataMap.put("jobLocator", this.jobLocator);

		return JobBuilder.newJob(CustomQuartzJob.class).withIdentity("demoJobTwo").setJobData(jobDataMap).storeDurably()
				.build();
	}

	@Bean
	public Trigger jobOneTrigger() {
		final SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(this.jobOneDetail()).withIdentity("jobOneTrigger")
				.withSchedule(scheduleBuilder).build();
	}

	@Bean
	public Trigger jobTwoTrigger() {
		final SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(20)
				.repeatForever();

		return TriggerBuilder.newTrigger().forJob(this.jobTwoDetail()).withIdentity("jobTwoTrigger")
				.withSchedule(scheduleBuilder).build();
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
		final SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(this.jobOneTrigger(), this.jobTwoTrigger());
		scheduler.setQuartzProperties(this.quartzProperties());
		scheduler.setJobDetails(this.jobOneDetail(), this.jobTwoDetail());
		return scheduler;
	}

	@Bean
	public Properties quartzProperties() throws IOException {
		final PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
}
